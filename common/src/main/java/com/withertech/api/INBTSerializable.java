package com.withertech.api;

import net.minecraft.nbt.Tag;

public interface INBTSerializable<T extends Tag>
{
	T serializeNBT();
	void deserializeNBT(T nbt);
}
