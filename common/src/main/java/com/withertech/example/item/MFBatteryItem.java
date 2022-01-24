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

package com.withertech.example.item;

import com.withertech.MineFlux;
import com.withertech.api.IMFContainer;
import com.withertech.api.IMFStorage;
import com.withertech.util.EnergyUtil;
import com.withertech.util.TextUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class MFBatteryItem extends Item implements IMFContainer
{
	public MFBatteryItem()
	{
		super(new Properties().stacksTo(1).tab(MineFlux.MINE_FLUX_TAB));
	}

	@Override
	public void verifyTagAfterLoad(@NotNull CompoundTag compoundTag)
	{
		if (!compoundTag.contains("Energy", Tag.TAG_INT))
			compoundTag.putInt("Energy", 0);
		if (!compoundTag.contains("Enabled", Tag.TAG_BYTE))
			compoundTag.putBoolean("Enabled", false);
		super.verifyTagAfterLoad(compoundTag);
	}

	@Override
	public void onCraftedBy(ItemStack itemStack, Level level, Player player)
	{
		EnergyUtil.insertEnergy(itemStack, 0, true);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, InteractionHand interactionHand)
	{
		ItemStack stack = player.getItemInHand(interactionHand);
		if (!stack.isEmpty() && !level.isClientSide() && player.isCrouching())
		{
			EnergyUtil.getEnergyStorage(stack).ifPresent(storage ->
				stack.getOrCreateTag().putBoolean("Enabled", !isEnabled(stack)));
		}
		return super.use(level, player, interactionHand);

	}

	@Override
	public boolean isFoil(ItemStack itemStack)
	{
		return isEnabled(itemStack);
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

	@Override
	public void inventoryTick(ItemStack fromStack, Level level, Entity entity, int slot, boolean bl)
	{
		if (!(fromStack.getItem() instanceof MFBatteryItem) ||
				getEnergy(fromStack) <= 0 ||
				!isEnabled(fromStack) ||
				!(entity instanceof Player))
			return;
		if (entity instanceof ServerPlayer player)
		{
			for (int i = 0; i < player.getInventory().getContainerSize(); i++)
			{
				if (i != slot)
				{
					ItemStack toStack = player.getInventory().getItem(i);
					if (toStack.getItem() instanceof MFBatteryItem)
					{
						if (!isEnabled(toStack))
						{
							chargeItem(fromStack, toStack);
						}
					}
					else
					{
						chargeItem(fromStack, toStack);
					}
				}
			}
		}
	}

	private static void chargeItem(ItemStack from, ItemStack to)
	{
		EnergyUtil.getEnergyStorage(from).ifPresent(fromStorage ->
				EnergyUtil.getEnergyStorage(to).ifPresent(toStorage ->
				{
					if (fromStorage.canExtract() && toStorage.canReceive())
					{
						final int maxExt = fromStorage.getEnergyStored();
						int simRec = toStorage.receiveEnergy(maxExt, true);
						int simExt = fromStorage.extractEnergy(simRec, true);
						if (simRec > 0 && simExt > 0)
						{
							fromStorage.extractEnergy(toStorage.receiveEnergy(maxExt, false), false);
						}
					}
				}));
	}

	private static int getEnergy(ItemStack stack)
	{
		return EnergyUtil.getEnergyStorage(stack).map(IMFStorage::getEnergyStored).orElse(0);
	}

	private static boolean isEnabled(ItemStack stack)
	{
		return stack.getOrCreateTag().getBoolean("Enabled");
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
		return getStorageFor(itemStack).map(energy -> Math.round(((float) energy.getEnergyStored() / (float) energy.getMaxEnergyStored()) * 13.0F)).orElse(0);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag)
	{
		if (itemStack.getItem() instanceof MFBatteryItem)
		{
			EnergyUtil.getEnergyStorage(itemStack).ifPresent(storage ->
					list.add(new TextComponent(TextUtil.getEnergyStorageString(storage))));
		}
		super.appendHoverText(itemStack, level, list, tooltipFlag);
	}


}
