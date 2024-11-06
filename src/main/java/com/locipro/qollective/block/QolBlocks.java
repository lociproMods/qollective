package com.locipro.qollective.block;

import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.custom.UnlitTorchBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class QolBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Qollective.MODID);

    public static final DeferredBlock<Block> UNLIT_TORCH = BLOCKS.register("unlit_torch",
            () -> new UnlitTorchBlock());
}
