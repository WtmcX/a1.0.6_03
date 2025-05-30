package net.minecraft.src;

import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.opengl.GlStateManager;
import net.peyton.eagler.v1_12.vec.Vec3D_112;
import org.lwjgl.opengl.GL11;

public class RenderHelper {
	private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);
	private static final Vec3D_112 LIGHT0_POS = (new Vec3D_112(0.20000000298023224D, 1.0D, -0.699999988079071D)).normalize();
	private static final Vec3D_112 LIGHT1_POS = (new Vec3D_112(-0.20000000298023224D, 1.0D, 0.699999988079071D)).normalize();

	public static void disableStandardItemLighting() {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHT0);
		GL11.glDisable(GL11.GL_LIGHT1);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	public static void enableStandardItemLighting() {
		GL11.glEnable(GL11.GL_LIGHTING);
		GlStateManager.enableMCLight(0, 0.6f, LIGHT0_POS.xCoord, LIGHT0_POS.yCoord, LIGHT0_POS.zCoord, 0.0D);
		GlStateManager.enableMCLight(1, 0.6f, LIGHT1_POS.xCoord, LIGHT1_POS.yCoord, LIGHT1_POS.zCoord, 0.0D);
		GlStateManager.setMCLightAmbient(0.4f, 0.4f, 0.4f);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	}

	private static FloatBuffer setColorBuffer(double var0, double var2, double var4, double var6) {
		return setColorBuffer((float)var0, (float)var2, (float)var4, (float)var6);
	}

	private static FloatBuffer setColorBuffer(float var0, float var1, float var2, float var3) {
		colorBuffer.clear();
		colorBuffer.put(var0).put(var1).put(var2).put(var3);
		colorBuffer.flip();
		return colorBuffer;
	}
}
