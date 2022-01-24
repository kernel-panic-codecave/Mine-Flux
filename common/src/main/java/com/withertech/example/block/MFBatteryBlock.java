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

package com.withertech.example.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.withertech.MineFlux;
import com.withertech.api.IMFStorage;
import com.withertech.example.item.MFBatteryBlockItem;
import com.withertech.example.tile.MFBatteryTile;
import com.withertech.util.EnergyUtil;
import dev.architectury.registry.block.BlockProperties;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class MFBatteryBlock extends Block implements EntityBlock
{
	public static final EnumProperty<FaceMode> NORTH;
	public static final EnumProperty<FaceMode> EAST;
	public static final EnumProperty<FaceMode> SOUTH;
	public static final EnumProperty<FaceMode> WEST;
	public static final EnumProperty<FaceMode> UP;
	public static final EnumProperty<FaceMode> DOWN;
	public static final Map<Direction, EnumProperty<FaceMode>> MODE_BY_DIRECTION;

	static
	{
		NORTH = EnumProperty.create("north", FaceMode.class);
		EAST = EnumProperty.create("east", FaceMode.class);
		SOUTH = EnumProperty.create("south", FaceMode.class);
		WEST = EnumProperty.create("west", FaceMode.class);
		UP = EnumProperty.create("up", FaceMode.class);
		DOWN = EnumProperty.create("down", FaceMode.class);
		MODE_BY_DIRECTION = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (enumMap) ->
		{
			enumMap.put(Direction.NORTH, NORTH);
			enumMap.put(Direction.EAST, EAST);
			enumMap.put(Direction.SOUTH, SOUTH);
			enumMap.put(Direction.WEST, WEST);
			enumMap.put(Direction.UP, UP);
			enumMap.put(Direction.DOWN, DOWN);
		}));
	}

	public MFBatteryBlock()
	{
		super(BlockProperties.of(Material.STONE).strength(0.5F));
		registerDefaultState(defaultBlockState()
				.setValue(NORTH, FaceMode.NONE)
				.setValue(EAST, FaceMode.NONE)
				.setValue(SOUTH, FaceMode.NONE)
				.setValue(WEST, FaceMode.NONE)
				.setValue(UP, FaceMode.NONE)
				.setValue(DOWN, FaceMode.NONE)
		);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
	{
		return !blockPlaceContext.getItemInHand().getOrCreateTag().contains("State")? super.getStateForPlacement(blockPlaceContext) : NbtUtils.readBlockState(blockPlaceContext.getItemInHand().getOrCreateTag().getCompound("State"));
	}

	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult)
	{
		if (player.isCrouching())
			level.setBlockAndUpdate(blockPos, blockState.cycle(MODE_BY_DIRECTION.get(blockHitResult.getDirection())));
		return InteractionResult.SUCCESS;
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity tileEntity, ItemStack itemStack)
	{
		if (!level.isClientSide())
		{
			ItemStack drop = new ItemStack(this);
			if (tileEntity instanceof MFBatteryTile tile)
			{
				drop.addTagElement("State", NbtUtils.writeBlockState(blockState));
				drop.addTagElement("Energy", IntTag.valueOf(tile.getStorageFor(null).map(IMFStorage::getEnergyStored).orElse(0)));
			}
			popResource(level, blockPos, drop);
		}
	}

	@Override
	public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack)
	{
		if (itemStack.getItem() instanceof MFBatteryBlockItem)
		{
			if (level.getBlockEntity(blockPos) instanceof MFBatteryTile tileEntity)
			{
				EnergyUtil.getEnergyStorage(itemStack).ifPresent(itemStorage ->
						EnergyUtil.getEnergyStorage(tileEntity).ifPresent(tileStorage ->
								tileStorage.deserializeNBT(itemStorage.serializeNBT())));
			}

		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
	{
		return MineFlux.BATTERY_TILE.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level world, @NotNull BlockState state, @NotNull BlockEntityType<T> type)
	{
		return type == MineFlux.BATTERY_TILE.get() ? MFBatteryTile::tick : null;
	}

}
