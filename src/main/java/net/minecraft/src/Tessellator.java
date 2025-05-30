package net.minecraft.src;

import net.lax1dude.eaglercraft.opengl.VertexFormat;
import net.lax1dude.eaglercraft.opengl.WorldVertexBufferUploader;

public class Tessellator {

	private net.lax1dude.eaglercraft.opengl.WorldRenderer worldRenderer;
	public static final Tessellator instance = new Tessellator(2097152);
	private VertexFormat format;

	private boolean hasTexture = false;
	private boolean hasColor = false;
	private boolean hasNormal = false;

	private double textureU = 0;
	private double textureV = 0;

	private int colorR;
	private int colorG;
	private int colorB;
	private int colorA;

	private float normalX;
	private float normalY;
	private float normalZ;

	private double xOffset;
	private double yOffset;
	private double zOffset;

	private Tessellator(int var1) {
		this.worldRenderer = new net.lax1dude.eaglercraft.opengl.WorldRenderer(var1);
	}

	public void draw() {
		this.worldRenderer.finishDrawing();
		WorldVertexBufferUploader.func_181679_a(this.worldRenderer);
		format = null;
		hasTexture = false;
		hasColor = false;
		hasNormal = false;
	}

	public void startDrawingQuads() {
		this.startDrawing(7);
	}

	public void startDrawing(int var1) {
		this.worldRenderer.begin(var1);
	}

	public void setTextureUV(double var1, double var3) {
		if(this.format == null) {
			this.format = new VertexFormat(true, false, false, false);
		} else {
			this.format = new VertexFormat(true, format.attribColorEnabled, format.attribNormalEnabled, false);
		}
		hasTexture = true;
		textureU = var1;
		textureV = var3;
	}

	public void setColorOpaque_F(float var1, float var2, float var3) {
		this.setColorOpaque((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F));
	}

	public void setColorRGBA_F(float var1, float var2, float var3, float var4) {
		this.setColorRGBA((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F), (int)(var4 * 255.0F));
	}

	public void setColorOpaque(int var1, int var2, int var3) {
		this.setColorRGBA(var1, var2, var3, 255);
	}

	public void setColorRGBA(int var1, int var2, int var3, int var4) {
		if(!this.worldRenderer.needsUpdate) {
			if(this.format == null) {
				this.format = new VertexFormat(false, true, false, false);
			} else {
				this.format = new VertexFormat(format.attribTextureEnabled, true, format.attribNormalEnabled, false);
			}
			this.hasColor = true;
			this.colorR = var1;
			this.colorG = var2;
			this.colorB = var3;
			this.colorA = var4;
		}
	}

	public void addVertexWithUV(double var1, double var3, double var5, double var7, double var9) {
		this.setTextureUV(var7, var9);
		this.addVertex(var1, var3, var5);
	}

	public void addVertex(double var1, double var3, double var5) {
		worldRenderer.setVertexFormat(format == null ? new VertexFormat(false, false, false, false) : format);

		worldRenderer.pos((float)(var1 + this.xOffset), (float)(var3 + this.yOffset), (float)(var5 + this.zOffset));

		if(this.hasTexture) {
			worldRenderer.tex(this.textureU, this.textureV);
		}

		if(this.hasColor) {
			worldRenderer.setColorRGBA(colorR, colorG, colorB, colorA);
		}

		if(this.hasNormal) {
			worldRenderer.normal(this.normalX, this.normalY, this.normalZ);
		}

		worldRenderer.endVertex();
	}

	public void setColorOpaque_I(int var1) {
		int var2 = var1 >> 16 & 255;
		int var3 = var1 >> 8 & 255;
		int var4 = var1 & 255;
		this.setColorOpaque(var2, var3, var4);
	}

	public void setColorRGBA_I(int var1, int var2) {
		int var3 = var1 >> 16 & 255;
		int var4 = var1 >> 8 & 255;
		int var5 = var1 & 255;
		this.setColorRGBA(var3, var4, var5, var2);
	}

	public void disableColor() {
		worldRenderer.markDirty();
	}

	public void setNormal(float var1, float var2, float var3) {
		if(this.format == null) {
			this.format = new VertexFormat(false, false, true, false);
		} else {
			this.format = new VertexFormat(format.attribTextureEnabled, format.attribColorEnabled, true, false);
		}
		hasNormal = true;
		normalX = var1;
		normalY = var2;
		normalZ = var3;
	}

	public void setTranslationD(double var1, double var3, double var5) {
		this.xOffset = var1;
		this.yOffset = var3;
		this.zOffset = var5;
	}

	public void setTranslationF(float var1, float var2, float var3) {
		this.xOffset += (double)var1;
		this.yOffset += (double)var2;
		this.zOffset += (double)var3;
	}
}