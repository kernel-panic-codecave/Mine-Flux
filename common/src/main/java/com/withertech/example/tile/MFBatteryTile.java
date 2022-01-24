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

package com.withertech.example.tile;

import com.withertech.MineFlux;
import com.withertech.api.IMFContainer;
import com.withertech.api.IMFStorage;
import com.withertech.example.block.FaceMode;
import com.withertech.example.block.MFBatteryBlock;
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

	public MFBatteryTile(BlockPos pos, BlockState state)
	{
		super(MineFlux.BATTERY_TILE.get(), pos, state);
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T tile)
	{
		Arrays.stream(Direction.values()).filter(direction -> tile.getBlockState().getValue(MFBatteryBlock.MODE_BY_DIRECTION.get(direction)) == FaceMode.OUTPUT).forEach(direction -> ((MFBatteryTile) tile).transferEnergy(direction));
	}

	@Override
	public Optional<IMFStorage> getStorageFor(Object that)
	{
		if (that == null) return Optional.of(energy);
		if (that instanceof Direction side)
		{
			if (getBlockState().getValue(MFBatteryBlock.MODE_BY_DIRECTION.get(side)) == FaceMode.NONE)
			{
				return Optional.empty();
			}
			return Optional.of(energy);
		}
		return Optional.empty();
	}

	protected void transferEnergy(Direction side)
	{
		BlockPos outPos = this.getBlockPos().relative(side);
		BlockEntity tileEntity = (this.level != null) ? this.level.getBlockEntity(outPos) : null;
		EnergyUtil.getEnergyStorage(this, side).ifPresent(fromStorage ->
				EnergyUtil.getEnergyStorage(tileEntity, side.getOpposite()).ifPresent(toStorage ->
				{
					if (fromStorage.canExtract() && toStorage.canReceive())
					{
						int maxExt = fromStorage.getEnergyStored();
						int simRec = toStorage.receiveEnergy(maxExt, true);
						int simExt = fromStorage.extractEnergy(simRec, true);
						if (simRec > 0 && simExt > 0)
						{
							fromStorage.extractEnergy(toStorage.receiveEnergy(maxExt, false), false);
						}
					}
				}));
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
		energy.deserializeNBT(compoundTag.get("Energy"));
		super.load(compoundTag);
	}
}
