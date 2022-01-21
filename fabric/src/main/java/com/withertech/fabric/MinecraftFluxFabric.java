package com.withertech.fabric;

import com.withertech.MinecraftFlux;
import com.withertech.example.fabric.MFBatteryBlockFabric;
import com.withertech.example.fabric.MFBatteryTileFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class MinecraftFluxFabric implements ModInitializer
{
	static
	{
		MinecraftFlux.BATTERY_BLOCK = MinecraftFlux.BLOCKS.register("battery", MFBatteryBlockFabric::new);
		MinecraftFlux.BATTERY_TILE = MinecraftFlux.TILES.register("battery", () -> FabricBlockEntityTypeBuilder.create(MFBatteryTileFabric::new, MinecraftFlux.BATTERY_BLOCK.get()).build());
	}

	@Override
	public void onInitialize()
	{
		MinecraftFlux.init();
	}
}