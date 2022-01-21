/*
 * Mine Flux
 * Copyright (C) 2022 WitherTech
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.withertech.example;

import com.withertech.MineFlux;
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
		super(MineFlux.BATTERY_TILE.get(), arg2, arg3);
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t)
	{
		Arrays.stream(Direction.values()).forEach(direction -> ((MFBatteryTile) t).transferEnergy(direction));
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
		this.energy.extractEnergy(EnergyUtil.insertTileEnergy(tileEntity, side.getOpposite(), out, false), false);
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
}
