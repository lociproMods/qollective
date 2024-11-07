package com.locipro.qollective.block.custom;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;

public class UnlitWallTorchBlock extends BaseTorchBlock {


    public UnlitWallTorchBlock(Properties properties) {
        super(properties);
    }
    @Override
    protected MapCodec<? extends BaseTorchBlock> codec() {
        return simpleCodec(UnlitTorchBlock::new);
    }

}
