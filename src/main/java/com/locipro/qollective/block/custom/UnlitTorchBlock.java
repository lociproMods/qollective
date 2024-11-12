package com.locipro.qollective.block.custom;


import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.BaseTorchBlock;


public class UnlitTorchBlock extends TickingTorch{

    public static final MapCodec<UnlitTorchBlock> CODEC = simpleCodec(UnlitTorchBlock::new);

    public UnlitTorchBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseTorchBlock> codec() {
        return CODEC;
    }


}
