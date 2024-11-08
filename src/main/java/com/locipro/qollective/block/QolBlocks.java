package com.locipro.qollective.block;

import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.custom.UnlitTorchBlock;
import com.locipro.qollective.block.custom.UnlitWallTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class QolBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Qollective.MODID);

    /*public static final DeferredBlock<Block> UNLIT_TORCH = BLOCKS.register("unlit_torch",
            () -> new UnlitTorchBlock(BlockBehaviour.Properties
                    .ofFullCopy(Blocks.TORCH)));*/
    public static final DeferredBlock<Block> UNLIT_TORCH = BLOCKS.registerBlock("unlit_torch",
            UnlitTorchBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH));

    public static final DeferredBlock<Block> UNLIT_WALL_TORCH = BLOCKS.registerBlock("unlit_wall_torch",
            UnlitWallTorchBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH));
}
