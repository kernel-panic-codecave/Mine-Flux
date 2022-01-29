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

package com.withertech.mine_flux.data.model;

import com.withertech.mine_flux.MineFlux;
import com.withertech.mine_flux.example.block.FaceMode;
import com.withertech.mine_flux.example.block.MFBatteryBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MFBlockStateProvider extends BlockStateProvider
{
	public MFBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper)
	{
		super(gen, MineFlux.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels()
	{
		BlockStates();
		ItemModels();
	}

	private void ItemModels()
	{
		itemModels().getBuilder(MineFlux.BATTERY_BLOCK_ITEM.get().getRegistryName().getPath()).parent(itemModels().getExistingFile(modLoc("block/battery_frame")));
		itemModels().getBuilder(MineFlux.BATTERY_ITEM.get().getRegistryName().getPath()).texture("layer0", modLoc("item/battery")).parent(itemModels().getExistingFile(mcLoc("item/generated")));
	}

	private void BlockStates()
	{
		batteryBlock();
	}

	private void batteryBlock()
	{
		getMultipartBuilder(MineFlux.BATTERY_BLOCK.get())
				.part()
				.modelFile(models().getExistingFile(modLoc("block/battery_frame")))
				.addModel()
				.end()
				.part()
				.modelFile(models().getExistingFile(modLoc("block/battery_input")))
				.addModel()
				.condition(MFBatteryBlock.NORTH, FaceMode.INPUT)
				.end()
				.part()
				.rotationY(90)
				.modelFile(models().getExistingFile(modLoc("block/battery_input")))
				.addModel()
				.condition(MFBatteryBlock.EAST, FaceMode.INPUT)
				.end()
				.part()
				.rotationY(180)
				.modelFile(models().getExistingFile(modLoc("block/battery_input")))
				.addModel()
				.condition(MFBatteryBlock.SOUTH, FaceMode.INPUT)
				.end()
				.part()
				.rotationY(270)
				.modelFile(models().getExistingFile(modLoc("block/battery_input")))
				.addModel()
				.condition(MFBatteryBlock.WEST, FaceMode.INPUT)
				.end()
				.part()
				.rotationX(270)
				.modelFile(models().getExistingFile(modLoc("block/battery_input")))
				.addModel()
				.condition(MFBatteryBlock.UP, FaceMode.INPUT)
				.end()
				.part()
				.rotationX(90)
				.modelFile(models().getExistingFile(modLoc("block/battery_input")))
				.addModel()
				.condition(MFBatteryBlock.DOWN, FaceMode.INPUT)
				.end()

				.part()
				.modelFile(models().getExistingFile(modLoc("block/battery_output")))
				.addModel()
				.condition(MFBatteryBlock.NORTH, FaceMode.OUTPUT)
				.end()
				.part()
				.rotationY(90)
				.modelFile(models().getExistingFile(modLoc("block/battery_output")))
				.addModel()
				.condition(MFBatteryBlock.EAST, FaceMode.OUTPUT)
				.end()
				.part()
				.rotationY(180)
				.modelFile(models().getExistingFile(modLoc("block/battery_output")))
				.addModel()
				.condition(MFBatteryBlock.SOUTH, FaceMode.OUTPUT)
				.end()
				.part()
				.rotationY(270)
				.modelFile(models().getExistingFile(modLoc("block/battery_output")))
				.addModel()
				.condition(MFBatteryBlock.WEST, FaceMode.OUTPUT)
				.end()
				.part()
				.rotationX(270)
				.modelFile(models().getExistingFile(modLoc("block/battery_output")))
				.addModel()
				.condition(MFBatteryBlock.UP, FaceMode.OUTPUT)
				.end()
				.part()
				.rotationX(90)
				.modelFile(models().getExistingFile(modLoc("block/battery_output")))
				.addModel()
				.condition(MFBatteryBlock.DOWN, FaceMode.OUTPUT)
				.end();
	}
}
