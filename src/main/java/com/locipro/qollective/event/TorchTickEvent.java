package com.locipro.qollective.event;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.locipro.qollective.Config;
import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.QolBlocks;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.*;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Qollective.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TorchTickEvent {

    private static final int BLOCKS_PER_TICK = 1;

    private static final ImmutableMap<? extends Block, Supplier<? extends Block>> torch_turnables = ImmutableMap.of(
            Blocks.TORCH, QolBlocks.UNLIT_TORCH,
            Blocks.WALL_TORCH, QolBlocks.UNLIT_WALL_TORCH
    );

    private static final ImmutableSet<? extends Block> wall_torches = ImmutableSet.of(
            Blocks.WALL_TORCH
    );

    // I am my own greatest enemy
    // I spent hours on this, trying to fix it, not understanding why it was only working for the first few chunks around 0,0
    // I, in my genius, used the CHUNK COORDINATES. (CHUNKPOS). FOR THE BLOCK GET/SET LOGIC.
    @SubscribeEvent
    public static void levelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel level) {
            if (!Config.rainTurnsOffTorches) return;

            final int RANDOM_TICK_SPEED = level.getGameRules().getRule(GameRules.RULE_RANDOMTICKING).get();
            if (level.random.nextFloat() > Config.torchTickChance * RANDOM_TICK_SPEED) return;

            ChunkMap chunkMap = level.getChunkSource().chunkMap;

            Iterator<Long> tickingChunksSet = chunkMap.getDistanceManager().getSpawnCandidateChunks();

            tickingChunksSet.forEachRemaining(cH -> {
                @Nullable
                ChunkHolder chunk = chunkMap.getVisibleChunkIfPresent(cH);

                if (chunk != null) {
                    // Make a list of block positions to attempt to convert
                    // CHUNK COORDS!!!!
                    Set<BlockPos> candidates = new LinkedHashSet<>(BLOCKS_PER_TICK);

                    for (int i = 0; i < BLOCKS_PER_TICK; i++) {
                        // Gets a random position in that chunk, at y = 0
                        // CHUNK COORDS!!!! (Y isn't a chunk coord)
                        BlockPos randomPosition = level.getBlockRandomPos(chunk.getPos().x, 0, chunk.getPos().z, 0);

                        BlockPos levelPos = chunk.getPos().getBlockAt(randomPosition.getX(), randomPosition.getY(), randomPosition.getZ());

                        // Adds that position (on the surface) to the list of positions to try to transform

                        // isn't actually on surface.
                        candidates.add(new BlockPos(
                                randomPosition.getX(),
                                level.getHeight(Heightmap.Types.WORLD_SURFACE, levelPos.getX(), levelPos.getZ()) - 1,
                                randomPosition.getZ()
                        ));
                    }

                    for (BlockPos pos : candidates) {
                        if (pos != null) {
                            BlockPos levelPos = chunk.getPos().getBlockAt(pos.getX(), pos.getY(), pos.getZ());

                            if (level.isRainingAt(levelPos) && level.canSeeSky(levelPos)) {

                                BlockState old = level.getBlockState(levelPos);

                                if (torch_turnables.containsKey(old.getBlock())) {
                                    // Won't be null (Inshallah)
                                    BlockState turned = torch_turnables.get(old.getBlock()).get().defaultBlockState();

                                    // Handle wall torch rotation!
                                    if (wall_torches.contains(old.getBlock())) {
                                        @Nullable Direction oldFacing = old.getValue(HorizontalDirectionalBlock.FACING);
                                        if (oldFacing != null) {
                                            turned = turned.setValue(HorizontalDirectionalBlock.FACING, oldFacing);
                                        }
                                    }

                                    level.setBlock(levelPos, turned, Block.UPDATE_ALL_IMMEDIATE | Block.UPDATE_SUPPRESS_DROPS);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

}