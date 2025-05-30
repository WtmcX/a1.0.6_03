package net.peyton.eagler.minecraft;

import net.minecraft.src.Entity;
import net.minecraft.src.World;

public interface EntityConstructor <T extends Entity> {

	T createEntity(World world);
}
