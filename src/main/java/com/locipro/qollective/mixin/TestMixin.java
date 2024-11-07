package com.locipro.qollective.mixin;

import com.locipro.qollective.Qollective;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TestMixin {
    @Inject(method = "init", at = @At("HEAD"))
    private void init(CallbackInfo ci) {
        Qollective.LOGGER.info("title screen mixin");
    }
}
