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

package com.withertech.fabric;

import com.withertech.MineFlux;
import com.withertech.example.fabric.MFBatteryBlockFabric;
import com.withertech.example.fabric.MFBatteryTileFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class MineFluxFabric implements ModInitializer
{
	static
	{
		MineFlux.BATTERY_BLOCK = MineFlux.BLOCKS.register("battery", MFBatteryBlockFabric::new);
		MineFlux.BATTERY_TILE = MineFlux.TILES.register("battery", () -> FabricBlockEntityTypeBuilder.create(MFBatteryTileFabric::new, MineFlux.BATTERY_BLOCK.get()).build());
	}

	@Override
	public void onInitialize()
	{
		MineFlux.init();
	}
}