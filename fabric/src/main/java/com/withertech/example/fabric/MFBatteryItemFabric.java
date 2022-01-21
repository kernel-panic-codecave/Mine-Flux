package com.withertech.example.fabric;

import com.withertech.api.IMFStorage;
import com.withertech.api.fabric.MFTagStorageFabric;
import com.withertech.example.MFBatteryItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MFBatteryItemFabric extends MFBatteryItem
{
	@Override
	protected Optional<IMFStorage> getStorageFor(@NotNull ItemStack stack)
	{
		return Optional.of(new MFTagStorageFabric(stack.getOrCreateTag(), 1_000));
	}
}
