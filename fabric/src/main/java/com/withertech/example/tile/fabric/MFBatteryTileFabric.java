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

package com.withertech.example.tile.fabric;

import com.withertech.api.fabric.MFStorageFabric;
import com.withertech.example.tile.MFBatteryTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MFBatteryTileFabric extends MFBatteryTile
{
	public MFBatteryTileFabric(BlockPos pos, BlockState state)
	{
		super(pos, state);
		this.energy = new MFStorageFabric(1_000_000, 100);
	}
}
