package com.withertech;

import com.withertech.example.MFBatteryBlock;
import com.withertech.example.MFBatteryTile;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MinecraftFlux
{
	public static final String MOD_ID = "minecraft_flux";

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

	public static RegistrySupplier<? extends MFBatteryBlock> BATTERY_BLOCK;
	public static RegistrySupplier<BlockEntityType<? extends MFBatteryTile>> BATTERY_TILE;

	public static void init()
	{
		if (Platform.isDevelopmentEnvironment())
		{
			BLOCKS.register();
			TILES.register();
		}
	}
}