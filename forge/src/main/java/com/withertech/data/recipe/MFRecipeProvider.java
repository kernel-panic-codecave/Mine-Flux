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

package com.withertech.data.recipe;

import com.withertech.MineFlux;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MFRecipeProvider extends RecipeProvider
{
	public MFRecipeProvider(DataGenerator arg)
	{
		super(arg);
	}

	@Override
	protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer)
	{
		ShapedRecipeBuilder.shaped(MineFlux.BATTERY_BLOCK.get())
				.pattern("iii")
				.pattern("iri")
				.pattern("iii")
				.define('i', Items.IRON_INGOT)
				.define('r', Items.REDSTONE_BLOCK)
				.unlockedBy("battery_block", has(Items.REDSTONE))
				.save(consumer);
		ShapedRecipeBuilder.shaped(MineFlux.BATTERY_ITEM.get())
				.pattern(" i ")
				.pattern("iri")
				.pattern("iii")
				.define('i', Items.IRON_NUGGET)
				.define('r', Items.REDSTONE)
				.unlockedBy("battery_item", has(Items.REDSTONE))
				.save(consumer);
	}
}
