package com.withertech.example.forge;

import com.withertech.api.forge.MFStorageForge;
import com.withertech.example.MFBatteryTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MFBatteryTileForge extends MFBatteryTile
{
	public MFBatteryTileForge(BlockPos arg2, BlockState arg3)
	{
		super(arg2, arg3);
		energy = new MFStorageForge(1_000);
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
	{
		if (cap == CapabilityEnergy.ENERGY)
			if (getStorageFor(side).isPresent())
				return LazyOptional.of(() -> energy).cast();
		return super.getCapability(cap, side);
	}
}
