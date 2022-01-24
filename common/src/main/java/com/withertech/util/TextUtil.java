package com.withertech.util;

import com.withertech.api.IMFStorage;
import org.jetbrains.annotations.NotNull;

public class TextUtil
{
	public static String getEnergyStorageString(@NotNull IMFStorage storage)
	{
		return "Energy: (" + storage.getEnergyStored() + "/" + storage.getMaxEnergyStored() + ")";
	}
}
