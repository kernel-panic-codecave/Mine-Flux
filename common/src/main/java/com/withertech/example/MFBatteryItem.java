package com.withertech.example;

import com.withertech.MineFlux;
import com.withertech.api.IMFContainer;
import com.withertech.api.IMFStorage;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public abstract class MFBatteryItem extends Item implements IMFContainer
{
	public MFBatteryItem()
	{
		super(new Properties().stacksTo(1));
	}

	@Override
	public void verifyTagAfterLoad(CompoundTag compoundTag)
	{
		if (!compoundTag.contains("Energy", Tag.TAG_INT))
			compoundTag.put("Energy", IntTag.valueOf(0));
		super.verifyTagAfterLoad(compoundTag);
	}

	@Override
	public Optional<IMFStorage> getStorageFor(Object that)
	{
		if (that instanceof ItemStack stack)
		{
			return getStorageFor(stack);
		}
		return Optional.empty();
	}

	protected abstract Optional<IMFStorage> getStorageFor(ItemStack stack);

	@Override
	public boolean isBarVisible(ItemStack itemStack)
	{
		return true;
	}

	@Override
	public int getBarColor(ItemStack itemStack)
	{
		return DyeColor.RED.getTextColor();
	}

	@Override
	public int getBarWidth(ItemStack itemStack)
	{
		return getStorageFor(itemStack).map(energy -> Math.round(((float) energy.getEnergyStored() / (float) energy.getMaxEnergyStored()) * 12.0F)).orElse(0);
	}


}
