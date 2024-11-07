package com.locipro.qollective.mixin;


import com.locipro.qollective.Config;
import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.QolBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*handlePrecipitation doesn't get called for torches? Anyways let's use a tick event fuck this bs*/
@Deprecated
@Mixin(Block.class)
public abstract class BlockMixin extends BlockBehaviour implements ItemLike, net.neoforged.neoforge.common.extensions.IBlockExtension {
    public BlockMixin(Properties properties) {
        super(properties);
    }


    /*@Inject(method = "handlePrecipitation(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/biome/Biome$Precipitation;)V", at = @At("HEAD"))
    public void handlePrecipitation(BlockState state, Level level, BlockPos pos, Biome.Precipitation precipitation, CallbackInfo ci) {
        //Qollective.LOGGER.info("AAAAA"); // WE ARE HERE!
        //Qollective.LOGGER.debug("Can see sky : {} Raining at pos : {} state.getBlock() : {}", level.canSeeSky(pos), level.isRainingAt(pos), state.getBlock());
        if (state.getBlock() == Blocks.TORCH) {
            Qollective.LOGGER.debug("SUSSY");
        }
        if (level.canSeeSky(pos) && level.isRainingAt(pos) && Config.rainTurnsOffTorches) {
            if (state.getBlock() == Blocks.TORCH) {
                Qollective.LOGGER.info("12312I3JWDHFKJSDHFJKSDHF IM GONNAKMS"); // Nothing
                level.setBlock(pos, QolBlocks.UNLIT_TORCH.get().defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE | Block.UPDATE_SUPPRESS_DROPS);

            }
            if (state.getBlock() == Blocks.WALL_TORCH) {
                Qollective.LOGGER.info("SHJFGJLKDSFHJGFKJSDFFHG"); // Nothing
                level.setBlock(pos, QolBlocks.UNLIT_TORCH.get().defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE | Block.UPDATE_SUPPRESS_DROPS);
            }
        }
    }*/
}
