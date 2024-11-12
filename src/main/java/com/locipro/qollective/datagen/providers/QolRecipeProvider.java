package com.locipro.qollective.datagen.providers;

import com.locipro.qollective.block.QolBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class QolRecipeProvider extends RecipeProvider{
    protected QolRecipeProvider(HolderLookup.Provider p_360573_, RecipeOutput p_360872_) {
        super(p_360573_, p_360872_);
    }

    @Override
    protected void buildRecipes() {
        HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);

        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.BUILDING_BLOCKS, QolBlocks.UNLIT_TORCH)
                .requires(Blocks.TORCH)
                .requires(ItemTags.DIRT)
                .unlockedBy("has_torch", has(Blocks.TORCH))
                .save(output);

    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new QolRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "QOLLECTIVE Mod Recipes";
        }
    }
}
