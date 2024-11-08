package com.locipro.qollective.event;

import com.locipro.qollective.Qollective;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.List;
import java.util.stream.Stream;

//@EventBusSubscriber(modid = Qollective.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TorchTickEvent {
    /*@SubscribeEvent
    public static void levelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel level) {
            List<ServerPlayer> players = level.players();
            for (ServerPlayer player : players) {
                // i give up ! yay
            }
        }
    }*/

}
/* isAreaLoaded ?
@Override
protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    if (!canBeGrass(state, level, pos)) {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
    } else {
        if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
        if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
            BlockState blockstate = this.defaultBlockState();

            for (int i = 0; i < 4; i++) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (level.getBlockState(blockpos).is(Blocks.DIRT) && canPropagate(blockstate, level, blockpos)) {
                    level.setBlockAndUpdate(
                            blockpos, blockstate.setValue(SNOWY, Boolean.valueOf(level.getBlockState(blockpos.above()).is(Blocks.SNOW)))
                    );
                }
            }
        }
    }
}*/