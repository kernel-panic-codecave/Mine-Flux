package com.withertech.example.item.fabric;

import com.withertech.api.IMFStorage;
import com.withertech.api.fabric.MFTagStorageFabric;
import com.withertech.example.item.MFBatteryBlockItem;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class MFBatteryBlockItemFabric extends MFBatteryBlockItem
{
	@Override
	protected Optional<IMFStorage> getStorageFor(ItemStack stack)
	{
		return Optional.of(new MFTagStorageFabric(stack.getOrCreateTag(), 1_000_000, 100));
	}
}
