package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.lax1dude.eaglercraft.EaglerOutputStream;
import net.lax1dude.eaglercraft.internal.IWebSocketClient;
import net.lax1dude.eaglercraft.internal.IWebSocketFrame;

public class NetworkManager {
	private boolean isRunning = true;
	private NetHandler netHandler;
	private List<Packet> readPackets = new ArrayList<Packet>();
	private boolean isServerTerminating = false;
	private boolean isTerminating = false;
	private String terminationReason = "";
	private int timeSinceLastRead = 0;
	private int sendQueueByteLength = 0;
	public int chunkDataSendCounter = 0;

	public IWebSocketClient webSocket;

	public NetworkManager(NetHandler var3) {
		this.netHandler = var3;
	}

	public void setWebsocketClient(IWebSocketClient client) {
		this.webSocket = client;
	}

	private EaglerOutputStream sendBuffer = new EaglerOutputStream();

	public void addToSendQueue(Packet var1) {
		if(!this.isServerTerminating) {
			sendBuffer.reset();
			try (DataOutputStream dos = new DataOutputStream(sendBuffer)) {
				Packet.writePacket(var1, dos);
				webSocket.send(sendBuffer.toByteArray());
			} catch(Exception e) {
				this.onNetworkError(e);
			}
		}
	}

	private void onNetworkError(Exception var1) {
		var1.printStackTrace();
		this.networkShutdown("\"Internal exception: \" + var1.toString()");
	}

	public void networkShutdown(String var1, Object... var2) {
		if(this.isRunning) {
			this.isTerminating = true;
			this.terminationReason = var1;
			this.isRunning = false;
			webSocket.close();
			webSocket = null;
		}
	}

	public void readPacket() {
		IWebSocketFrame frame;
		while((frame = webSocket.getNextBinaryFrame()) != null) {
			byte[] arr = frame.getByteArray();
			if(arr != null) {
				try(ByteArrayInputStream bais = new ByteArrayInputStream(arr); DataInputStream packetStream = new DataInputStream(bais)) {
					Packet pkt = Packet.readPacket(packetStream);
					if(pkt != null) {
						this.readPackets.add(pkt);
					} else {
						this.networkShutdown("disconnect.endOfStream", new Object[0]);
					}
				} catch(IOException e) {
					if(!this.isTerminating) {
						this.onNetworkError(e);
					}
				}
			}
		}
	}

	public void processReadPackets() {
		if(this.sendQueueByteLength > 1048576) {
			this.networkShutdown("disconnect.overflow", new Object[0]);
		}

		if(this.readPackets.isEmpty()) {
			if(this.timeSinceLastRead++ == 1200) {
				this.networkShutdown("disconnect.timeout", new Object[0]);
			}
		} else {
			this.timeSinceLastRead = 0;
		}

		int var1 = 100;

		while(!this.readPackets.isEmpty() && var1-- >= 0) {
			Packet var2 = (Packet)this.readPackets.remove(0);
			var2.processPacket(this.netHandler);
		}

		if(this.isTerminating && this.readPackets.isEmpty()) {
			this.netHandler.handleErrorMessage(this.terminationReason);
		}
	}

	static boolean isRunning(NetworkManager var0) {
		return var0.isRunning;
	}

	static boolean isServerTerminating(NetworkManager var0) {
		return var0.isServerTerminating;
	}
}