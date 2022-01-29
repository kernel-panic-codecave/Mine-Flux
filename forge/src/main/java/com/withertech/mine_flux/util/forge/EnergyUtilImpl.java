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

package com.withertech.mine_flux.util.forge;

import com.withertech.mine_flux.api.IMFStorage;
import com.withertech.mine_flux.api.forge.MFStorageForge;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.CapabilityEnergy;

import java.util.Optional;

public class EnergyUtilImpl
{
	public static int insertEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return tile != null ? tile.getCapability(CapabilityEnergy.ENERGY, facing).map(storage -> storage.receiveEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static int extractEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return tile != null ? tile.getCapability(CapabilityEnergy.ENERGY, facing).map(storage -> storage.extractEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static int insertEnergy(ItemStack stack, int energy, boolean simulate)
	{
		return stack != null ? stack.getCapability(CapabilityEnergy.ENERGY).map(storage -> storage.receiveEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static int extractEnergy(ItemStack stack, int energy, boolean simulate)
	{
		return stack != null ? stack.getCapability(CapabilityEnergy.ENERGY).map(storage -> storage.extractEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static Optional<IMFStorage> getEnergyStorage(BlockEntity tile, Direction facing)
	{
		return tile != null ? tile.getCapability(CapabilityEnergy.ENERGY, facing).map(storage -> (IMFStorage) storage) : Optional.empty();
	}

	public static Optional<IMFStorage> getEnergyStorage(ItemStack stack)
	{
		return stack != null ? stack.getCapability(CapabilityEnergy.ENERGY).map(storage -> (IMFStorage) storage) : Optional.empty();
	}

	public static IMFStorage create(int capacity)
	{
		return new MFStorageForge(capacity);
	}

	public static IMFStorage create(int capacity, int maxTransfer)
	{
		return new MFStorageForge(capacity, maxTransfer);
	}

	public static IMFStorage create(int capacity, int maxReceive, int maxExtract)
	{
		return new MFStorageForge(capacity, maxReceive, maxExtract);
	}

	public static IMFStorage create(int capacity, int maxReceive, int maxExtract, int energy)
	{
		return new MFStorageForge(capacity, maxReceive, maxExtract, energy);
	}
}
