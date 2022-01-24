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

package com.withertech.util;

import com.withertech.api.IMFStorage;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Optional;

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

	@ExpectPlatform
	public static int insertEnergy(ItemStack stack, int energy, boolean simulate)
	{
		throw new AssertionError();
	}

	@ExpectPlatform
	public static int extractEnergy(ItemStack stack, int energy, boolean simulate)
	{
		throw new AssertionError();
	}

	public static Optional<IMFStorage> getEnergyStorage(BlockEntity tile)
	{
		return getEnergyStorage(tile, null);
	}

	@ExpectPlatform
	public static Optional<IMFStorage> getEnergyStorage(BlockEntity tile, Direction facing)
	{
		throw new AssertionError();
	}

	@ExpectPlatform
	public static Optional<IMFStorage> getEnergyStorage(ItemStack stack)
	{
		throw new AssertionError();
	}
}
