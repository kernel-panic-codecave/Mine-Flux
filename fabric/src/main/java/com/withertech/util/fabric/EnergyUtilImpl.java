package com.withertech.util.fabric;

import com.withertech.api.IMFContainer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EnergyUtilImpl
{
	public static int insertEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return (tile instanceof IMFContainer)? ((IMFContainer) tile).getStorageFor(facing).map(imeStorage -> imeStorage.receiveEnergy(energy, simulate)).orElse(0):0;
	}

	public static int extractEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return (tile instanceof IMFContainer)? ((IMFContainer) tile).getStorageFor(facing).map(imeStorage -> imeStorage.extractEnergy(energy, simulate)).orElse(0):0;
	}
}
