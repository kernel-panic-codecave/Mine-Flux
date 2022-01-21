package com.withertech.forge;

import com.withertech.example.forge.MFBatteryBlockForge;
import com.withertech.example.forge.MFBatteryTileForge;
import dev.architectury.platform.forge.EventBuses;
import com.withertech.MinecraftFlux;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MinecraftFlux.MOD_ID)
public class MinecraftFluxForge
{
	static
	{
		MinecraftFlux.BATTERY_BLOCK = MinecraftFlux.BLOCKS.register("battery", MFBatteryBlockForge::new);
		MinecraftFlux.BATTERY_TILE = MinecraftFlux.TILES.register("battery", () -> BlockEntityType.Builder.of(MFBatteryTileForge::new, MinecraftFlux.BATTERY_BLOCK.get()).build(null));
	}
	public MinecraftFluxForge()
	{
		// Submit our event bus to let architectury register our content on the right time
		EventBuses.registerModEventBus(MinecraftFlux.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
		MinecraftFlux.init();
	}
}