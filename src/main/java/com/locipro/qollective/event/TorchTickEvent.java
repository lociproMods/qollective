package com.locipro.qollective.event;

import com.locipro.qollective.Qollective;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.List;

@EventBusSubscriber(modid = Qollective.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TorchTickEvent {
    @SubscribeEvent
    public static void levelTick(LevelTickEvent.Post event) {
        /*List<? extends Player> players = event.getLevel().players();
        for (Player player : players) {
            BlockPos pos = player.getOnPos();
        }*/
    }
}
