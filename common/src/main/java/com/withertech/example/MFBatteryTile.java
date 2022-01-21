package com.withertech.example;

import com.withertech.MinecraftFlux;
import com.withertech.api.IMFContainer;
import com.withertech.api.IMFStorage;
import com.withertech.util.EnergyUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.Optional;

public abstract class MFBatteryTile extends BlockEntity implements IMFContainer
{
	protected IMFStorage energy;
	public MFBatteryTile(BlockPos arg2, BlockState arg3)
	{
		super(MinecraftFlux.BATTERY_TILE.get(), arg2, arg3);
	}

	@Override
	public Optional<IMFStorage> getStorageFor(Object that)
	{
		return Optional.of(energy);
	}

	protected void transferEnergy(Direction side)
	{
		BlockPos outPos = this.getBlockPos().relative(side);
		BlockEntity tileEntity = this.level.getBlockEntity(outPos);
		int out = Math.min(energy.getMaxExtract(), this.energy.getEnergyStored());
		this.energy.extractEnergy(EnergyUtil.insertEnergy(tileEntity, side.getOpposite(), out, false), false);
	}

	@Override
	protected void saveAdditional(CompoundTag compoundTag)
	{
		compoundTag.put("Energy", energy.serializeNBT());
		super.saveAdditional(compoundTag);
	}

	@Override
	public void load(CompoundTag compoundTag)
	{
		energy.deserializeNBT(compoundTag.getCompound("Energy"));
		super.load(compoundTag);
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t)
	{
		Arrays.stream(Direction.values()).forEach(direction -> ((MFBatteryTile)t).transferEnergy(direction));
	}
}
