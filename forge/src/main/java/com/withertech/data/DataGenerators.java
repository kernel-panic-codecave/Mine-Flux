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

package com.withertech.data;

import com.withertech.data.lang.MFLangProvider;
import com.withertech.data.model.MFBlockStateProvider;
import com.withertech.data.recipe.MFRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class DataGenerators
{
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event)
	{
		final DataGenerator generator = event.getGenerator();
		generator.addProvider(new MFLangProvider(generator));
		generator.addProvider(new MFBlockStateProvider(generator, event.getExistingFileHelper()));
		generator.addProvider(new MFRecipeProvider(generator));
	}
}
