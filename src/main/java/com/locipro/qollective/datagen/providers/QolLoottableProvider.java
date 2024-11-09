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
        dropSelf(QolBlocks.UNLIT_TORCH.get());
        dropOther(QolBlocks.UNLIT_WALL_TORCH.get(), QolBlocks.UNLIT_TORCH);
    }
    // These are all the blocks that will need a loot table generated.
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return QolBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
