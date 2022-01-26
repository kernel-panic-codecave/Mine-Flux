package com.withertech.config;

import com.withertech.MineFlux;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = MineFlux.MOD_ID)
public class MFConfig implements ConfigData
{
	@ConfigEntry.Gui.RequiresRestart
	public boolean enableExampleContent = true;
}
