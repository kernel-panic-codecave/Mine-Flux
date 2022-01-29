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

package com.withertech.mine_flux;

import com.withertech.mine_flux.config.MFConfig;
import com.withertech.mine_flux.example.block.MFBatteryBlock;
import com.withertech.mine_flux.example.item.MFBatteryItem;
import com.withertech.mine_flux.example.tile.MFBatteryTile;
import com.withertech.mine_flux.recipe.RuntimeRecipe;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class MineFlux
{
	public static final String MOD_ID = "mine_flux";

	public static final boolean CLOTH_CONFIG_PRESENT = Platform.isModLoaded("cloth-config");

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

	public static RegistrySupplier<? extends MFBatteryItem> BATTERY_ITEM;
	public static final CreativeModeTab MINE_FLUX_TAB = CreativeTabRegistry.create(modLoc("mine_flux"), () -> (!CLOTH_CONFIG_PRESENT || AutoConfig.getConfigHolder(MFConfig.class).getConfig().enableExampleContent) ? new ItemStack(BATTERY_ITEM.get()) : ItemStack.EMPTY).setRecipeFolderName("mine_flux");
	public static RegistrySupplier<? extends MFBatteryBlock> BATTERY_BLOCK;
	public static RegistrySupplier<BlockEntityType<? extends MFBatteryTile>> BATTERY_TILE;
	public static RegistrySupplier<BlockItem> BATTERY_BLOCK_ITEM;
	public static Map<Pair<ResourceLocation, ResourceLocation>, RuntimeRecipe> RECIPES = new HashMap<>();

	public static void init()
	{
		LifecycleEvent.SETUP.register(MineFlux::setup);
		if (CLOTH_CONFIG_PRESENT) Platform.getMod(MOD_ID).registerConfigurationScreen(parent -> AutoConfig.getConfigScreen(MFConfig.class, parent).get());
		if (!CLOTH_CONFIG_PRESENT || AutoConfig.getConfigHolder(MFConfig.class).getConfig().enableExampleContent)
		{
			BLOCKS.register();
			ITEMS.register();
			TILES.register();
		}
	}




	public static ResourceLocation modLoc(String loc)
	{
		return new ResourceLocation(MOD_ID, loc);
	}

	private static void setup()
	{
		if (!CLOTH_CONFIG_PRESENT || AutoConfig.getConfigHolder(MFConfig.class).getConfig().enableExampleContent)
		{
			RECIPES.putAll(RuntimeRecipe.createRecipes(recipeConsumer ->
			{
				ShapedRecipeBuilder.shaped(MineFlux.BATTERY_BLOCK.get())
						.pattern("iii")
						.pattern("iri")
						.pattern("iii")
						.define('i', Items.IRON_INGOT)
						.define('r', Items.REDSTONE_BLOCK)
						.unlockedBy("battery_block", RuntimeRecipe.has(Items.REDSTONE))
						.save(recipeConsumer);
				ShapedRecipeBuilder.shaped(MineFlux.BATTERY_ITEM.get())
						.pattern(" i ")
						.pattern("iri")
						.pattern("iii")
						.define('i', Items.IRON_NUGGET)
						.define('r', Items.REDSTONE)
						.unlockedBy("battery_item", RuntimeRecipe.has(Items.REDSTONE))
						.save(recipeConsumer);
			}));
		}
	}
}