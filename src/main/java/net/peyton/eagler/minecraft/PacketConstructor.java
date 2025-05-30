package net.peyton.eagler.minecraft;

import net.minecraft.src.Packet;

public interface PacketConstructor<T extends Packet> {
	T createPacket();
}
