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
