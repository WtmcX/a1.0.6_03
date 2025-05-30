package org.lwjgl.opengl;

import net.lax1dude.eaglercraft.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;

public class GL11 extends EaglercraftGPU {

	public static void glEnable(int p1) {
		switch (p1) {
		case GL_DEPTH_TEST:
			GL11.enableDepth();
			break;
		case GL_CULL_FACE:
			GL11.enableCull();
			break;
		case GL_BLEND:
			GL11.enableBlend();
			break;
		case GL_RESCALE_NORMAL:
			break;
		case GL_TEXTURE_2D:
			GL11.enableTexture2D();
			break;
		case GL_LIGHTING:
			GL11.enableLighting();
			break;
		case GL_LIGHT0:
			GL11.enableMCLight(0);
			break;
		case GL_LIGHT1:
			GL11.enableMCLight(1);
			break;
		case GL_ALPHA_TEST:
			GL11.enableAlpha();
			break;
		case GL_FOG:
			GL11.enableFog();
			break;
		case GL_COLOR_MATERIAL:
		    GL11.enableColorMaterial();
		    break;
		case GL_TEXTURE_GEN_S:
		case GL_TEXTURE_GEN_T:
		case GL_TEXTURE_GEN_R:
		case GL_TEXTURE_GEN_Q:
			GL11.enableTexGen();
			break;
		case GL_POLYGON_OFFSET_FILL:
			GL11.enablePolygonOffset();
			break;
		default:
			break;
		}
	}

	public static void glDisable(int p1) {
		switch (p1) {
		case GL_DEPTH_TEST:
			GL11.disableDepth();
			break;
		case GL_CULL_FACE:
			GL11.disableCull();
			break;
		case GL_BLEND:
			GL11.disableBlend();
			break;
		case GL_RESCALE_NORMAL:
			break;
		case GL_TEXTURE_2D:
			GL11.disableTexture2D();
			break;
		case GL_LIGHTING:
			GL11.disableLighting();
			break;
		case GL_LIGHT0:
			GL11.disableMCLight(0);
			break;
		case GL_LIGHT1:
			GL11.disableMCLight(1);
			break;
		case GL_ALPHA_TEST:
			GL11.disableAlpha();
			break;
		case GL_FOG:
			GL11.disableFog();
			break;
		case GL_COLOR_MATERIAL:
		    GL11.disableColorMaterial();
		    break;
		case GL_TEXTURE_GEN_S:
		case GL_TEXTURE_GEN_T:
		case GL_TEXTURE_GEN_R:
		case GL_TEXTURE_GEN_Q:
			GL11.disableTexGen();
			break;
		case GL_POLYGON_OFFSET_FILL:
			GL11.disablePolygonOffset();
			break;
		default:
			break;
		}
	}

	public static void glShadeModel(int i) {
		GL11.shadeModel(i);
	}

	public static void glClearDepth(float f) {
		GL11.clearDepth(f);
	}

	public static void glClearDepth(double d) {
		GL11.clearDepth((float) d);
	}

	public static void glDepthFunc(int f) {
		GL11.depthFunc(f);
	}

	public static void glAlphaFunc(int i, float f) {
		GL11.alphaFunc(i, f);
	}

	public static void glCullFace(int i) {
		GL11.cullFace(i);
	}

	public static void glMatrixMode(int i) {
		GL11.matrixMode(i);
	}

	public static void glLoadIdentity() {
		GL11.loadIdentity();
	}

	public static void glViewport(int i, int j, int width, int height) {
		GL11.viewport(i, j, width, height);
	}

	public static void glColorMask(boolean b, boolean c, boolean d, boolean e) {
		GL11.colorMask(b, c, d, e);
	}

	public static void glClearColor(float fogRed, float fogBlue, float fogGreen, float f) {
		GL11.clearColor(fogRed, fogBlue, fogGreen, f);
	}

	public static void glClear(int i) {
		GL11.clear(i);
	}

	public static void glTranslatef(float f, float g, float h) {
		GL11.translate(f, g, h);
	}

	public static void glRotatef(float f, float g, float h, float i) {
		GL11.rotate(f, g, h, i);
	}

	public static void glColor4f(float f, float g, float h, float i) {
		GL11.color(f, g, h, i);
	}

	public static void glBindTexture(int i, int var110) {
		if (i != GL_TEXTURE_2D) {
			throw new RuntimeException("Only 2D texture types are supported!");
		}
		GL11.bindTexture(var110);
	}

	public static void glBlendFunc(int i, int j) {
		GL11.blendFunc(i, j);
	}

	public static void glPushMatrix() {
		GL11.pushMatrix();
	}

	public static void glPopMatrix() {
		GL11.popMatrix();
	}

	public static void glScalef(float f, float var35, float var352) {
		GL11.scale(f, var35, var352);
	}

	public static void glDepthMask(boolean b) {
		GL11.depthMask(b);
	}

	public static void glCallLists(IntBuffer p1) {
		while (p1.hasRemaining()) {
			glCallList(p1.get());
		}
	}

	public static void glOrtho(double d, double var3, double var2, double e, double f, double g) {
		GL11.ortho(d, var3, var2, e, f, g);
	}

	public static void glGenTextures(IntBuffer idBuffer) {
		for (int i = idBuffer.position(); i < idBuffer.limit(); i++) {
			idBuffer.put(i, GL11.generateTexture());
		}
	}

	public static void glGetFloat(int glModelviewMatrix, FloatBuffer modelviewBuff) {
		GL11.getFloat(glModelviewMatrix, modelviewBuff);
	}

	public static void glColor3f(float f, float g, float h) {
		GL11.color(f, g, h);
	}

	public static void glColorMaterial(int i, int j) {
	}

	public static void glPolygonOffset(float f, float g) {
		doPolygonOffset(f, g);
	}

	public static void glScaled(double f, double f1, double f2) {
		glScalef((float)f, (float)f1, (float)f2);
	}
	
	public static void glDeleteTexture(int texture) {
		deleteTexture(texture);
	}

	public static void glDeleteTextures(IntBuffer buffer) {
		while (buffer.hasRemaining()) {
			glDeleteTexture(buffer.get());
		}
	}

	public static void glFogf(int type, float param) {
		switch(type) {
		case GL_FOG_DENSITY:
			setFogDensity(param);
			return;
		case GL_FOG_START:
			setFogStart(param);
			return;
		case GL_FOG_END:
			setFogEnd(param);
			return;
		default:
			return; //?
		}
	}

	public static void glFogi(int type, int param) {
		switch(type) {
		case GL_FOG_MODE:
			setFog(param);
			return;
		default:
			return; //?
		}
	}
}