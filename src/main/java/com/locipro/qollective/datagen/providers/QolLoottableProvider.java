package com.locipro.qollective.datagen.providers;

import com.locipro.qollective.block.QolBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

public class QolLoottableProvider extends BlockLootSubProvider {
    public QolLoottableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }
    @Override
    protected void generate() {
        dropOther(QolBlocks.UNLIT_TORCH.get(), Blocks.TORCH);
    }
    // These are all the blocks that will need a loot table generated.
    // If you want a block to drop nothing, simply add ".noLootTable()" to its builder pattern when making the block
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return QolBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
