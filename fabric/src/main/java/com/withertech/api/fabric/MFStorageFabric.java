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

package com.withertech.api.fabric;

import com.withertech.api.IMFStorage;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;

public class MFStorageFabric implements IMFStorage
{
	protected int energy;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;

	public MFStorageFabric(int capacity)
	{
		this(capacity, capacity, capacity, 0);
	}

	public MFStorageFabric(int capacity, int maxTransfer)
	{
		this(capacity, maxTransfer, maxTransfer, 0);
	}

	public MFStorageFabric(int capacity, int maxReceive, int maxExtract)
	{
		this(capacity, maxReceive, maxExtract, 0);
	}

	public MFStorageFabric(int capacity, int maxReceive, int maxExtract, int energy)
	{
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
		this.energy = Math.max(0, Math.min(capacity, energy));
	}

	@Override
	public int getMaxReceive()
	{
		return maxReceive;
	}

	@Override
	public int getMaxExtract()
	{
		return maxExtract;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		if (!canReceive())
			return 0;

		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
		if (!simulate)
			energy += energyReceived;
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		if (!canExtract())
			return 0;

		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		if (!simulate)
			energy -= energyExtracted;
		return energyExtracted;
	}

	@Override
	public int getEnergyStored()
	{
		return energy;
	}

	@Override
	public int getMaxEnergyStored()
	{
		return capacity;
	}

	@Override
	public boolean canExtract()
	{
		return this.maxExtract > 0;
	}

	@Override
	public boolean canReceive()
	{
		return this.maxReceive > 0;
	}

	@Override
	public Tag serializeNBT()
	{
		return IntTag.valueOf(this.getEnergyStored());
	}

	@Override
	public void deserializeNBT(Tag nbt)
	{
		if (!(nbt instanceof IntTag intNbt))
			throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
		this.energy = intNbt.getAsInt();
	}
}
