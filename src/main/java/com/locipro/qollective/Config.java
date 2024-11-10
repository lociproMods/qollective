package com.locipro.qollective;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = Qollective.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue TORCHES_LIGHT_OFF_RAIN = BUILDER
            .comment("Whether to turn off torches when it rains or not")
            .define("rainTurnsOffTorches", true);

    private static final ModConfigSpec.DoubleValue TORCHES_TICK_CHANCE = BUILDER
            .comment("The base chance of a level tick to trigger a torch-turn-off tick. \n" +
                    "later gets multiplied by the random tick speed gamerule to determine whether a torch should turn off during rain.")
            .defineInRange("torchTickChance", 0.66, 0, 1.0);

    /*private static final ModConfigSpec.ConfigValue<List<? extends String>> TORCH_IDS = BUILDER.comment(
            "A map of torch block to unlit torch block."
    )*/

    /*private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");*/

    // a list of strings that are treated as resource locations for items
    /*private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);
*/
    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean rainTurnsOffTorches;
    public static double torchTickChance;
/*    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;*/

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        /*logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();*/
        rainTurnsOffTorches = TORCHES_LIGHT_OFF_RAIN.get();
        torchTickChance = TORCHES_TICK_CHANCE.get();

        // convert the list of strings into a set of items
        /*items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());*/
    }
}
