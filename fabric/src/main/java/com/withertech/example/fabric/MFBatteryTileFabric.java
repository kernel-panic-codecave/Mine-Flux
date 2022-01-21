package com.withertech.example.fabric;

import com.withertech.api.fabric.MFStorageFabric;
import com.withertech.example.MFBatteryTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MFBatteryTileFabric extends MFBatteryTile
{
	public MFBatteryTileFabric(BlockPos arg2, BlockState arg3)
	{
		super(arg2, arg3);
		this.energy = new MFStorageFabric(1_000);
	}
}
