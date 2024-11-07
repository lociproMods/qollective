package com.locipro.qollective.block.custom;

import com.locipro.qollective.Config;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class UnlitTorchBlock extends BaseTorchBlock {
    //private boolean scheduledToTurnOff = false;

    public static final MapCodec<UnlitTorchBlock> CODEC = simpleCodec(UnlitTorchBlock::new);

    @Override
    protected @NotNull MapCodec<? extends BaseTorchBlock> codec() {
        return CODEC;
    }

    public UnlitTorchBlock(Properties properties) {
        super(properties);
    }


    // Make it revert
    /*@Override
    protected BlockState updateShape(BlockState p_304418_, Direction p_304475_, BlockState p_304669_, LevelAccessor p_304637_, BlockPos p_304633_, BlockPos p_304603_) {
        BlockState state =super.updateShape(p_304418_, p_304475_, p_304669_, p_304637_, p_304633_, p_304603_);
        return state;
    }*/

}
