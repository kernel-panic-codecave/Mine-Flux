package com.withertech.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EnergyUtil
{
	@ExpectPlatform
	public static int insertEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		throw new AssertionError();
	}

	@ExpectPlatform
	public static int extractEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		throw new AssertionError();
	}
}
