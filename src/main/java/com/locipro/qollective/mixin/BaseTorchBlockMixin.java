package com.locipro.qollective.mixin;

import com.locipro.qollective.Config;
import com.locipro.qollective.Qollective;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.LocalTime;
import java.util.Random;

// Schedules a tick for all torch blocks
// can't i guess
// Let's use an event to schedule a tick.
// how about we not
@Deprecated
@Mixin(BaseTorchBlock.class)
public abstract class BaseTorchBlockMixin extends Block {
    public BaseTorchBlockMixin(Properties properties) {
        super(properties);
    }

    // Doesn't run on every tick.
    /*@Inject(method = "updateShape", at = @At(value = "RETURN"))
    protected void updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighbourPos, CallbackInfoReturnable<BlockState> cir) {
        Qollective.LOGGER.info("we are at update shape!");
        if (Config.rainTurnsOffTorches) {
            if (state.is(Blocks.TORCH) || state.is(Blocks.WALL_TORCH)) {
                Random random = new Random(LocalTime.now().getSecond());
                int roll = random.nextInt(0, 300);
                Qollective.LOGGER.info("{}", roll);
                if (roll == 269) {
                    levelAccessor.scheduleTick(pos, state.getBlock(), 1);
                    Qollective.LOGGER.info("Scheduled a tick for a torch block");
                }
            }
        }
    }*/


}
