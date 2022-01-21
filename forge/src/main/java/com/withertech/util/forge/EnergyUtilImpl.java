package com.withertech.util.forge;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.CapabilityEnergy;

public class EnergyUtilImpl
{
	public static int insertEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return tile == null ? 0 : tile.getCapability(CapabilityEnergy.ENERGY, facing).map((storage) -> storage.receiveEnergy(energy, simulate)).orElse(0);
	}

	public static int extractEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return tile == null ? 0 : tile.getCapability(CapabilityEnergy.ENERGY, facing).map((storage) -> storage.extractEnergy(energy, simulate)).orElse(0);
	}
}
