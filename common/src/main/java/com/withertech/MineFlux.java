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

package com.withertech;

import com.withertech.example.block.MFBatteryBlock;
import com.withertech.example.item.MFBatteryItem;
import com.withertech.example.tile.MFBatteryTile;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MineFlux
{
	public static final String MOD_ID = "mine_flux";

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

	public static RegistrySupplier<? extends MFBatteryItem> BATTERY_ITEM;
	public static RegistrySupplier<? extends MFBatteryBlock> BATTERY_BLOCK;
	public static RegistrySupplier<BlockEntityType<? extends MFBatteryTile>> BATTERY_TILE;


	public static final CreativeModeTab MINE_FLUX_TAB = CreativeTabRegistry.create(modLoc("mine_flux"), () -> new ItemStack(BATTERY_ITEM.get())).setRecipeFolderName("mine_flux");

	public static RegistrySupplier<BlockItem> BATTERY_BLOCK_ITEM;

	public static void init()
	{
		BLOCKS.register();
		ITEMS.register();
		TILES.register();
	}

	public static ResourceLocation modLoc(String loc)
	{
		return new ResourceLocation(MOD_ID, loc);
	}
}