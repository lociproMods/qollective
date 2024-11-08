package com.locipro.qollective.event;

import com.locipro.qollective.Qollective;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
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
import java.util.Set;
import java.util.stream.Stream;

@EventBusSubscriber(modid = Qollective.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TorchTickEvent {
    public static final int blocksPerChunk = 3;
    @SubscribeEvent
    public static void levelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel level) {
            if (!level.isRaining()) return;
            if (level.getRandom().nextBoolean()) return;

            ChunkMap map = level.getChunkSource().chunkMap;
            LongIterator tickingChunks = map.getDistanceManager().getSpawnCandidateChunks();

            BlockPos[] positions = new BlockPos[blocksPerChunk];

            tickingChunks.forEachRemaining(aLong -> {
                ChunkHolder chunk = map.getVisibleChunkIfPresent(aLong);
                assert chunk != null;

                for (int i = 0; i < level.getGameRules().getRule(GameRules.RULE_RANDOMTICKING).get(); i++) {
                    BlockPos pos = level.getBlockRandomPos(chunk.getPos().x, 0, chunk.getPos().z, 0);
                    positions[i] = pos;
                }
            });



            for (BlockPos pos : positions) {
                // Test if it's a torch!
            }
        }
    }

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