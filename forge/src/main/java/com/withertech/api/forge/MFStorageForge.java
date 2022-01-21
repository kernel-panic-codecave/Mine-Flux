package com.withertech.api.forge;

import com.withertech.api.IMFStorage;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

public class MFStorageForge implements IMFStorage, IEnergyStorage, INBTSerializable<Tag>
{
	protected int energy;
	protected int capacity;
	protected int maxReceive;

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

	protected int maxExtract;

	public MFStorageForge(int capacity)
	{
		this(capacity, capacity, capacity, 0);
	}

	public MFStorageForge(int capacity, int maxTransfer)
	{
		this(capacity, maxTransfer, maxTransfer, 0);
	}

	public MFStorageForge(int capacity, int maxReceive, int maxExtract)
	{
		this(capacity, maxReceive, maxExtract, 0);
	}

	public MFStorageForge(int capacity, int maxReceive, int maxExtract, int energy)
	{
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
		this.energy = Math.max(0 , Math.min(capacity, energy));
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
