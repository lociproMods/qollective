package com.locipro.qollective.block;

import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.custom.TickingTorch;
import com.locipro.qollective.block.custom.UnlitTorchBlock;
import com.locipro.qollective.block.custom.UnlitWallTorchBlock;
import com.locipro.qollective.item.QolItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.function.Function;

public class QolBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Qollective.MODID);

    private static <T extends Block> void registerBlockItem(DeferredBlock<T> block, Item.Properties properties) {
        QolItems.ITEMS.registerSimpleBlockItem(block, new Item.Properties());
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Function<BlockBehaviour.Properties, ? extends T> func, BlockBehaviour.Properties props) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, func, props);
        registerBlockItem(toReturn, new Item.Properties());
        return toReturn;
    }
    /*public static final DeferredBlock<Block> UNLIT_TORCH = BLOCKS.register("unlit_torch",
            () -> new UnlitTorchBlock(BlockBehaviour.Properties
                    .ofFullCopy(Blocks.TORCH)));*/
    // Block item handled separately
    public static final DeferredBlock<TickingTorch> UNLIT_TORCH = BLOCKS.registerBlock("unlit_torch",
            UnlitTorchBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)
                    .lightLevel(state -> 0));

    public static final DeferredBlock<TickingTorch> UNLIT_WALL_TORCH = BLOCKS.registerBlock("unlit_wall_torch",
            UnlitWallTorchBlock::new, BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .lightLevel(p_220869_ -> 0)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY));
}
