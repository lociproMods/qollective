package com.locipro.qollective.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class UnlitTorchBlock extends BaseTorchBlock {

    public static final MapCodec<UnlitTorchBlock> CODEC = simpleCodec(UnlitTorchBlock::new);

    protected UnlitTorchBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState p_304669_, LevelAccessor levelAccessor, BlockPos pos, BlockPos p_304603_) {
        if (direction == Direction.DOWN && !this.canSurvive(state, levelAccessor, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        // TODO HOW TO CHECK FOR RAAAAAAIAN
        if (levelAccessor.canSeeSky(pos) && levelAccessor.) {

        }
        return super.updateShape(state, direction, p_304669_, levelAccessor, pos, p_304603_);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseTorchBlock> codec() {
        return CODEC;
    }
}
