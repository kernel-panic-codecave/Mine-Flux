package com.withertech.api.fabric;

import net.minecraft.nbt.CompoundTag;

public class MFTagStorageFabric extends MFStorageFabric
{
	private final CompoundTag tag;
	public MFTagStorageFabric(CompoundTag tag, int capacity)
	{
		super(capacity);
		this.tag = tag;
		deserializeNBT(tag.get("Energy"));
	}

	public MFTagStorageFabric(CompoundTag tag, int capacity, int maxTransfer)
	{
		super(capacity, maxTransfer);
		this.tag = tag;
		deserializeNBT(tag.get("Energy"));
	}

	public MFTagStorageFabric(CompoundTag tag, int capacity, int maxReceive, int maxExtract)
	{
		super(capacity, maxReceive, maxExtract);
		this.tag = tag;
		deserializeNBT(tag.get("Energy"));
	}

	public MFTagStorageFabric(CompoundTag tag, int capacity, int maxReceive, int maxExtract, int energy)
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
