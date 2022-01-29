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

package com.withertech.mine_flux.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RuntimeRecipe
{
	private final JsonObject RECIPE;
	private final JsonObject ADVANCEMENT;

	protected RuntimeRecipe(JsonObject recipe, JsonObject advancement)
	{
		RECIPE = recipe;
		ADVANCEMENT = advancement;
	}

	public static Map<Pair<ResourceLocation, ResourceLocation>, RuntimeRecipe> createRecipes(Consumer<Consumer<FinishedRecipe>> recipeConsumer)
	{
		Map<Pair<ResourceLocation, ResourceLocation>, RuntimeRecipe> map = new HashMap<>();
		recipeConsumer.accept(finishedRecipe -> map.put(Pair.of(finishedRecipe.getId(), finishedRecipe.getAdvancementId()), new RuntimeRecipe(finishedRecipe.serializeRecipe(), finishedRecipe.serializeAdvancement())));
		return map;
	}

	public JsonObject getRecipe()
	{
		return RECIPE;
	}

	public JsonObject getAdvancement()
	{
		return ADVANCEMENT;
	}

	public static InventoryChangeTrigger.TriggerInstance has(ItemLike arg) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(arg).build());
	}

	public static InventoryChangeTrigger.TriggerInstance has(Tag<Item> arg) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(arg).build());
	}

	public static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... args) {
		return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, args);
	}
}
