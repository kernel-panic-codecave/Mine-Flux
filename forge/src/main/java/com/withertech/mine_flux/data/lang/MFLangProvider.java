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

package com.withertech.mine_flux.data.lang;

import com.withertech.mine_flux.MineFlux;
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
		addConfigTranslations();
	}

	private String confKey(String key)
	{
		return "text.autoconfig.mine_flux." + key;
	}

	private void addConfigTranslations()
	{
		add(confKey("title"), "Mine Flux");
		add(confKey("option.enableExampleContent"), "Enable Example Content");
	}
}
