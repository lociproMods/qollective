package com.locipro.qollective.event;

import com.google.common.collect.ImmutableMap;
import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.QolBlocks;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.*;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Qollective.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TorchTickEvent {
    public static final int BLOCKS_PER_TICK = 1;
    private static final float TICK_CHANCE = 0.01f;

    private static final ImmutableMap<? extends Block, Supplier<? extends Block>> torch_turnables = ImmutableMap.of(
            Blocks.TORCH, QolBlocks.UNLIT_TORCH,
            Blocks.WALL_TORCH, QolBlocks.UNLIT_WALL_TORCH
    );

    @SubscribeEvent
    public static void levelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel level) {
            final int RANDOM_TICK_SPEED = level.getGameRules().getRule(GameRules.RULE_RANDOMTICKING).get();
            if (level.random.nextFloat() > TICK_CHANCE * RANDOM_TICK_SPEED) return;

            ChunkMap chunkMap = level.getChunkSource().chunkMap;

            //LongIterator tickingChunks = chunkMap.getDistanceManager().getSpawnCandidateChunks();
            LongSet tickingChunksSet = chunkMap.getDistanceManager().getTickingChunks();


            // For each ticking chunk
            for (Long aLong : tickingChunksSet) {
                // Get the chunk
                ChunkHolder chunk = chunkMap.getVisibleChunkIfPresent(aLong);

                if (chunk != null) {
                    // Make a list of block positions to attempt to convert
                    Set<BlockPos> candidates = new LinkedHashSet<>(BLOCKS_PER_TICK);

                    for (int i = 0; i < BLOCKS_PER_TICK; i++) {
                        // Gets a random position in that chunk, at y = 0
                        BlockPos randomPosition = level.getBlockRandomPos(chunk.getPos().x, 0, chunk.getPos().z, 0);

                        // Adds that position (on the surface) to the list of positions to try to transform
                        candidates.add(new BlockPos(
                                randomPosition.getX(),
                                level.getHeight(Heightmap.Types.WORLD_SURFACE, randomPosition.getX(), randomPosition.getZ()) - 1,
                                randomPosition.getZ()
                        ));
                    }


                    for (BlockPos pos : candidates) {
                        if (pos != null && level.isRainingAt(pos) && level.canSeeSky(pos)) {

                            Block blockAtPos = level.getBlockState(pos).getBlock();

                            if (torch_turnables.containsKey(blockAtPos) &&
                                    torch_turnables.get(blockAtPos).get() != null) {

                                BlockState newState = torch_turnables.get(blockAtPos).get().defaultBlockState();

                                level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE | Block.UPDATE_SUPPRESS_DROPS);
                            }
                        }
                    }
                }
            }



            /*// for each CHUNK
            tickingChunks.forEachRemaining(aLong -> {
                List<BlockPos> positions = new ArrayList<>(blocksToChangePerTick);

                ChunkHolder chunk = chunkMap.getVisibleChunkIfPresent(aLong);
                assert chunk != null;

                for (int i = 0; i < RANDOM_TICK_SPEED; i++) {
                    if (positions.size() >= blocksToChangePerTick) break;

                    BlockPos pos = level.getBlockRandomPos(chunk.getPos().x, 0, chunk.getPos().z, 0);

                    // I dont know how to use height maps
                    positions.add(new BlockPos(
                            pos.getX(),
                            level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()) - 1
                            ,pos.getZ()));
                }
                for (BlockPos pos : positions) {
                    if (pos != null) {
                        if (!level.isRainingAt(pos.above()) || !level.canSeeSky(pos)) return;
                        Block blockAtPos = level.getBlockState(pos).getBlock();
                        if (torch_turnables.containsKey(blockAtPos) && torch_turnables.get(blockAtPos).get() != null) {
                            BlockState newState = torch_turnables.get(blockAtPos).get().defaultBlockState();
                            level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE | Block.UPDATE_SUPPRESS_DROPS);
                        }
                    }
                }
            });*/
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