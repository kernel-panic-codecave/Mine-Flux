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

package com.withertech.mine_flux.fabric;

import com.withertech.mine_flux.MineFlux;
import com.withertech.mine_flux.config.MFConfig;
import com.withertech.mine_flux.example.block.fabric.MFBatteryBlockFabric;
import com.withertech.mine_flux.example.item.fabric.MFBatteryBlockItemFabric;
import com.withertech.mine_flux.example.item.fabric.MFBatteryItemFabric;
import com.withertech.mine_flux.example.tile.fabric.MFBatteryTileFabric;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class MineFluxFabric implements ModInitializer
{
	static
	{
		MineFlux.BATTERY_ITEM = MineFlux.ITEMS.register("battery_item", MFBatteryItemFabric::new);
		MineFlux.BATTERY_BLOCK = MineFlux.BLOCKS.register("battery", MFBatteryBlockFabric::new);
		MineFlux.BATTERY_BLOCK_ITEM = MineFlux.ITEMS.register("battery_block", MFBatteryBlockItemFabric::new);
		MineFlux.BATTERY_TILE = MineFlux.TILES.register("battery", () -> FabricBlockEntityTypeBuilder.create(MFBatteryTileFabric::new, MineFlux.BATTERY_BLOCK.get()).build());
	}

	@Override
	public void onInitialize()
	{
		if (MineFlux.CLOTH_CONFIG_PRESENT) AutoConfig.register(MFConfig.class, GsonConfigSerializer::new);
		MineFlux.init();
	}
}