package net.minecraft.src;

public class EntityOtherPlayerMP extends EntityPlayer {
	public EntityOtherPlayerMP(World var1, String var2) {
		super(var1);
		this.username = var2;
		this.yOffset = 1.62F;
		if(var2 != null && var2.length() > 0) {
			this.skinUrl = "http://www.minecraft.net/skin/" + var2 + ".png";
			System.out.println("Loading texture " + this.skinUrl);
		}

		this.noClip = true;
	}

	public void onUpdate() {
		super.onUpdate();
	}

	public void onLivingUpdate() {
	}
}
