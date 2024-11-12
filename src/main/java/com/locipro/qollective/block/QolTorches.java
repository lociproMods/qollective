package com.locipro.qollective.block;

import com.locipro.qollective.block.custom.TickingTorch;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.checkerframework.checker.units.qual.K;

import java.util.Map;
import java.util.function.Supplier;

public class QolTorches {
    public static Map<Block, TickingTorch> CONVERSION_MAP;
    private static boolean init = false;

    public static void initTorches(Map<Block, TickingTorch> map) {
        if (init) return;

        CONVERSION_MAP = map;
        init = true;
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
