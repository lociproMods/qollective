package com.locipro.qollective.datagen.providers;

import com.locipro.qollective.Qollective;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.locipro.qollective.Qollective.MODID;

public class QolItemModleProvider extends ItemModelProvider {
    public QolItemModleProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
