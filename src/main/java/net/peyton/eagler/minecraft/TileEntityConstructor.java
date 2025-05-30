package net.peyton.eagler.minecraft;

import net.minecraft.src.TileEntity;

public interface TileEntityConstructor<T extends TileEntity> {
	T createTileEntity();
}