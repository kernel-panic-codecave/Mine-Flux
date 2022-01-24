package com.withertech.data.lang;

import com.withertech.MineFlux;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.data.LanguageProvider;

public class MFLangProvider extends LanguageProvider
{
	public MFLangProvider(DataGenerator gen)
	{
		super(gen, MineFlux.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations()
	{
		addBlock(MineFlux.BATTERY_BLOCK, "Big Battery");
		addItem(MineFlux.BATTERY_ITEM, "Battery");
		add(((TranslatableComponent) MineFlux.MINE_FLUX_TAB.getDisplayName()).getKey(), "Mine Flux");
	}
}
