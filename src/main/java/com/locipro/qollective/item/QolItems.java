package com.locipro.qollective.item;

import com.locipro.qollective.Qollective;
import com.locipro.qollective.block.QolBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class QolItems {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(Qollective.MODID);

    /* public static final Item TORCH = registerBlock(
        Blocks.TORCH, (p_370971_, p_370972_) -> new StandingAndWallBlockItem(p_370971_, Blocks.WALL_TORCH, Direction.DOWN, p_370972_)
    );*/
    //public static final DeferredItem<Item> UNLIT_TORCH = ITEMS.registerBlock("unlit_torch", (thisInstance, itemProperties) -> new StandingAndWallBlockItem(thisInstance, QolBlocks.UNLIT_WALL_TORCH.get(), Direction.DOWN, itemProperties));

    public static final DeferredItem<? extends BlockItem> UNLIT_TORCH = ITEMS.registerItem("unlit_torch",
            (properties) -> new StandingAndWallBlockItem(
                    QolBlocks.UNLIT_TORCH.get(),
                    QolBlocks.UNLIT_WALL_TORCH.get(),
                    Direction.DOWN,
                    properties), new Item.Properties().useBlockDescriptionPrefix());

}
