package com.withertech.example;

import com.withertech.MinecraftFlux;
import dev.architectury.registry.block.BlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MFBatteryBlock extends Block implements EntityBlock
{
	public MFBatteryBlock()
	{
		super(BlockProperties.copy(Blocks.STONE));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
	{
		return MinecraftFlux.BATTERY_TILE.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level world, @NotNull BlockState state, @NotNull BlockEntityType<T> type)
	{
		return type == MinecraftFlux.BATTERY_TILE.get() ? MFBatteryTile::tick : null;
	}
}
