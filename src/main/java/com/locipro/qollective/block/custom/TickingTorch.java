package com.locipro.qollective.block.custom;


import com.locipro.qollective.block.QolTorches;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;


public abstract class TickingTorch extends BaseTorchBlock {
    protected static final BooleanProperty scheduledToYeet = BooleanProperty.create("scheduled_to_yeet");


    // make a BLOCK proeprty somehow so that you can revert
    // how do you get a blockstate as a blockstate property
    // im not just gonna get a damn STRING and fucking pass that as the id of a block and get it with
    // builtinregisters.gets or sowhatever idkkfjsdf

    // ok you kind of cant but let's use a map (for conversion and revertion (if that;'s how you spell reverison idk))

    protected TickingTorch(Properties p_304955_) {
        super(p_304955_.randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(scheduledToYeet, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(scheduledToYeet);
    }


    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }


    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(scheduledToYeet)) {
            handleYeet(state, level, pos, random);
            return;
        }

        boolean inRain = level.isRainingAt(pos);
        if (!inRain) {
            level.setBlockAndUpdate(pos, state.setValue(scheduledToYeet, true));
        }
    }



    public void handleYeet(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Block original = QolTorches.getKey(QolTorches.CONVERSION_MAP, state.getBlock());
        if (original != null) {
            BlockState newState = original.defaultBlockState();
            if (newState.hasProperty(BlockStateProperties.FACING)) {
                newState = newState.setValue(BlockStateProperties.FACING, state.getValue(BlockStateProperties.FACING));
            }
            level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE | Block.UPDATE_SUPPRESS_DROPS);
        }
    }
}
