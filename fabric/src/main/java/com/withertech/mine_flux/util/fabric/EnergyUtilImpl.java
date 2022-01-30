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

package com.withertech.mine_flux.util.fabric;

import com.withertech.mine_flux.api.IMFContainer;
import com.withertech.mine_flux.api.IMFStorage;
import com.withertech.mine_flux.api.fabric.MFStorageFabric;
import com.withertech.mine_flux.api.fabric.MFTagStorageFabric;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EnergyUtilImpl
{
	public static int insertEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return (tile instanceof IMFContainer) ? ((IMFContainer) tile).getStorageFor(facing).map(imeStorage -> imeStorage.receiveEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static int extractEnergy(BlockEntity tile, Direction facing, int energy, boolean simulate)
	{
		return (tile instanceof IMFContainer) ? ((IMFContainer) tile).getStorageFor(facing).map(imeStorage -> imeStorage.extractEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static int insertEnergy(ItemStack stack, int energy, boolean simulate)
	{
		return (stack != null && stack.getItem() instanceof IMFContainer) ? ((IMFContainer) stack.getItem()).getStorageFor(stack).map(imfStorage -> imfStorage.receiveEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static int extractEnergy(ItemStack stack, int energy, boolean simulate)
	{
		return (stack != null && stack.getItem() instanceof IMFContainer) ? ((IMFContainer) stack.getItem()).getStorageFor(stack).map(imfStorage -> imfStorage.extractEnergy(energy, simulate)).orElse(0) : 0;
	}

	public static Optional<IMFStorage> getEnergyStorage(BlockEntity tile, Direction facing)
	{
		return (tile instanceof IMFContainer) ? ((IMFContainer) tile).getStorageFor(facing) : Optional.empty();
	}

	public static Optional<IMFStorage> getEnergyStorage(ItemStack stack)
	{
		return (stack != null && stack.getItem() instanceof IMFContainer) ? ((IMFContainer) stack.getItem()).getStorageFor(stack) : Optional.empty();
	}

	@Contract("_ -> new")
	public static @NotNull IMFStorage create(int capacity)
	{
		return new MFStorageFabric(capacity);
	}

	@Contract("_, _ -> new")
	public static @NotNull IMFStorage create(int capacity, int maxTransfer)
	{
		return new MFStorageFabric(capacity, maxTransfer);
	}

	@Contract("_, _, _ -> new")
	public static @NotNull IMFStorage create(int capacity, int maxReceive, int maxExtract)
	{
		return new MFStorageFabric(capacity, maxReceive, maxExtract);
	}

	@Contract("_, _, _, _ -> new")
	public static @NotNull IMFStorage create(int capacity, int maxReceive, int maxExtract, int energy)
	{
		return new MFStorageFabric(capacity, maxReceive, maxExtract, energy);
	}

	@Contract("_, _ -> new")
	public static @NotNull IMFStorage create(CompoundTag tag, int capacity)
	{
		return new MFTagStorageFabric(tag, capacity);
	}

	@Contract("_, _, _ -> new")
	public static @NotNull IMFStorage create(CompoundTag tag, int capacity, int maxTransfer)
	{
		return new MFTagStorageFabric(tag, capacity, maxTransfer);
	}

	@Contract("_, _, _, _ -> new")
	public static @NotNull IMFStorage create(CompoundTag tag, int capacity, int maxReceive, int maxExtract)
	{
		return new MFTagStorageFabric(tag, capacity, maxReceive, maxExtract);
	}

	@Contract("_, _, _, _, _ -> new")
	public static @NotNull IMFStorage create(CompoundTag tag, int capacity, int maxReceive, int maxExtract, int energy)
	{
		return new MFTagStorageFabric(tag, capacity, maxReceive, maxExtract, energy);
	}
}
