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

package com.withertech.mine_flux.forge;

import com.withertech.mine_flux.MineFlux;
import com.withertech.mine_flux.config.MFConfig;
import com.withertech.mine_flux.data.DataGenerators;
import com.withertech.mine_flux.example.block.forge.MFBatteryBlockForge;
import com.withertech.mine_flux.example.item.forge.MFBatteryBlockItemForge;
import com.withertech.mine_flux.example.item.forge.MFBatteryItemForge;
import com.withertech.mine_flux.example.tile.forge.MFBatteryTileForge;
import dev.architectury.platform.forge.EventBuses;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MineFlux.MOD_ID)
public class MineFluxForge
{
	static
	{
		MineFlux.BATTERY_ITEM = MineFlux.ITEMS.register("battery_item", MFBatteryItemForge::new);
		MineFlux.BATTERY_BLOCK = MineFlux.BLOCKS.register("battery", MFBatteryBlockForge::new);
		MineFlux.BATTERY_BLOCK_ITEM = MineFlux.ITEMS.register("battery_block", MFBatteryBlockItemForge::new);
		MineFlux.BATTERY_TILE = MineFlux.TILES.register("battery", () -> BlockEntityType.Builder.of(MFBatteryTileForge::new, MineFlux.BATTERY_BLOCK.get()).build(null));
	}

	public MineFluxForge()
	{
		FMLJavaModLoadingContext.get().getModEventBus().register(DataGenerators.class);
		// Submit our event bus to let architectury register our content on the right time
		EventBuses.registerModEventBus(MineFlux.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
		AutoConfig.register(MFConfig.class, Toml4jConfigSerializer::new);
		MineFlux.init();
	}
}