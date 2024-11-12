package com.locipro.qollective.datagen;

import com.locipro.qollective.Qollective;
import com.locipro.qollective.datagen.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Qollective.MODID)
public class QolDatagenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        // Data generators may require some of these as constructor parameters.
        // See below for more details on each of these.
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();




        generator.addProvider(
                event.includeServer(),
                new LootTableProvider(output, Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(QolLoottableProvider::new, LootContextParamSets.BLOCK)),
                        lookupProvider)
        );

        BlockTagsProvider blockTagsProvider = new QolBlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(
                event.includeServer(),
                blockTagsProvider
        );

        generator.addProvider(
                event.includeServer(),
                new QolRecipeProvider.Runner(output, lookupProvider)
        );

        generator.addProvider(
                event.includeClient(),
                new QolBlockModelProvider(output, event.getExistingFileHelper())
        );
        generator.addProvider(
                event.includeClient(),
                new QolItemModleProvider(output, event.getExistingFileHelper())
        );


    }
}