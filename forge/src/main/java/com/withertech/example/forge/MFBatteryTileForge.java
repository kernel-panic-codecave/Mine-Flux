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

package com.withertech.example.forge;

import com.withertech.api.IMFStorage;
import com.withertech.api.forge.MFStorageForge;
import com.withertech.example.MFBatteryTile;
import it.unimi.dsi.fastutil.objects.ObjectArrayPriorityQueue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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
		{
			return getStorageFor(side).map(imfStorage -> LazyOptional.of(() -> imfStorage)).orElse(LazyOptional.empty()).cast();
		}
		return super.getCapability(cap, side);
	}
}
