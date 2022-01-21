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

package com.withertech.api.forge;

import net.minecraft.nbt.CompoundTag;

public class MFTagStorageForge extends MFStorageForge
{
	private final CompoundTag tag;
	public MFTagStorageForge(CompoundTag tag, int capacity)
	{
		super(capacity);
		this.tag = tag;
		deserializeNBT(tag.get("Energy"));
	}

	public MFTagStorageForge(CompoundTag tag, int capacity, int maxTransfer)
	{
		super(capacity, maxTransfer);
		this.tag = tag;
		deserializeNBT(tag.get("Energy"));
	}

	public MFTagStorageForge(CompoundTag tag, int capacity, int maxReceive, int maxExtract)
	{
		super(capacity, maxReceive, maxExtract);
		this.tag = tag;
		deserializeNBT(tag.get("Energy"));
	}

	public MFTagStorageForge(CompoundTag tag, int capacity, int maxReceive, int maxExtract, int energy)
	{
		super(capacity, maxReceive, maxExtract, energy);
		this.tag = tag;
		deserializeNBT(tag.get("Energy"));
	}

	public void updateTag()
	{
		tag.put("Energy", serializeNBT());
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		final int received = super.receiveEnergy(maxReceive, simulate);
		updateTag();
		return received;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		final int extracted = super.extractEnergy(maxExtract, simulate);
		updateTag();
		return extracted;
	}
}
