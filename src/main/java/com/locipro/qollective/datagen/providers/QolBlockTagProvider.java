package com.locipro.qollective.datagen.providers;

import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.QolBlocks;
import com.locipro.qollective.block.QolTorches;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class QolBlockTagProvider extends BlockTagsProvider {
    public QolBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Qollective.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.WALL_POST_OVERRIDE)
                .add(QolBlocks.UNLIT_TORCH.get())
                .add(QolBlocks.UNLIT_WALL_TORCH.get());
    }
}
