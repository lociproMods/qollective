package com.locipro.qollective.datagen.providers;

import com.locipro.qollective.Qollective;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.locipro.qollective.Qollective.MODID;

public class QolBlockModelProvider extends BlockStateProvider    {
    public QolBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
    /*private void createNormalTorch(Block torchBlock, Block wallTorchBlock) {
        TextureMapping texturemapping = TextureMapping.torch(torchBlock);
        this.blockStateOutput.accept(createSimpleBlock(torchBlock, ModelTemplates.TORCH.create(torchBlock, texturemapping, this.modelOutput)));
        this.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(
                                        wallTorchBlock,
                                        Variant.variant().with(VariantProperties.MODEL, ModelTemplates.WALL_TORCH.create(wallTorchBlock, texturemapping, this.modelOutput))
                                )
                                .with(createTorchHorizontalDispatch())
                );
        this.createSimpleFlatItemModel(torchBlock);
        this.skipAutoItemBlock(wallTorchBlock);
    }*/
}
