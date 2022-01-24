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

package com.withertech.forge;

import com.withertech.MineFlux;
import com.withertech.data.DataGenerators;
import com.withertech.example.block.forge.MFBatteryBlockForge;
import com.withertech.example.item.forge.MFBatteryBlockItemForge;
import com.withertech.example.item.forge.MFBatteryItemForge;
import com.withertech.example.tile.forge.MFBatteryTileForge;
import dev.architectury.platform.forge.EventBuses;
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
		MineFlux.init();
	}
}