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
