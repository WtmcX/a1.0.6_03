package net.minecraft.src;

import net.lax1dude.eaglercraft.Random;

public class BlockObsidian extends BlockStone {
	public BlockObsidian(int var1, int var2) {
		super(var1, var2);
	}

	public int quantityDropped(Random var1) {
		return 1;
	}

	public int idDropped(int var1, Random var2) {
		return Block.obsidian.blockID;
	}
}
