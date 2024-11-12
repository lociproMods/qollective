package com.locipro.qollective.datagen.providers;

import com.locipro.qollective.block.QolBlocks;
import com.locipro.qollective.block.custom.TickingTorch;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

public class QolLoottableProvider extends BlockLootSubProvider {
    public QolLoottableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }
    @Override
    protected void generate() {
        add(QolBlocks.UNLIT_TORCH.get(), createTorchDrops(QolBlocks.UNLIT_TORCH.get(), Blocks.TORCH));
        add(QolBlocks.UNLIT_WALL_TORCH.get(), createTorchDrops(QolBlocks.UNLIT_TORCH.get(), Blocks.TORCH));
    }




    protected LootTable.Builder createTorchDrops(Block unlitTorch, Block litTorch) {
        return this.createSilkTouchOrShearsOrPersistentDispatchTable(
                unlitTorch,
                applyExplosionCondition(unlitTorch, LootItem.lootTableItem(litTorch)));
    }
    // These are all the blocks that will need a loot table generated.
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return QolBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createSilkTouchOrShearsOrPersistentDispatchTable(Block block, LootPoolEntryContainer.Builder<?> builder) {
        return createSelfDropDispatchTable(block, hasShears().or(hasShears()).or(
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(BlockStateProperties.PERSISTENT, true))
        ), builder);
    }
}
