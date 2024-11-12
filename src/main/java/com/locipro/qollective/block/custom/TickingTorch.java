package com.locipro.qollective.block.custom;


import com.locipro.qollective.block.QolTorches;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.Nullable;


// Weird... The wall torches become persistent and scheduled to yeet when they get converted... Weird...
public abstract class TickingTorch extends BaseTorchBlock {
    public static final BooleanProperty scheduledToYeet = BooleanProperty.create("scheduled_to_yeet");

    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;

    // make a BLOCK proeprty somehow so that you can revert
    // how do you get a blockstate as a blockstate property
    // im not just gonna get a damn STRING and fucking pass that as the id of a block and get it with
    // builtinregisters.gets or sowhatever idkkfjsdf

    // ok you kind of cant but let's use a map (for conversion and revertion (if that;'s how you spell reverison idk))

    protected TickingTorch(Properties p_304955_) {
        super(p_304955_);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(scheduledToYeet, false)
                .setValue(PERSISTENT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(scheduledToYeet, PERSISTENT);
    }



    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }


    // Doesn't get invoked on level.setBlock, only when players place.
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(PERSISTENT, true);
    }


    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        boolean inRain = level.isRainingAt(pos);

        level.setBlockAndUpdate(pos, state.setValue(scheduledToYeet, !inRain));


        if (state.getValue(scheduledToYeet)) {
            revert(state, level, pos, random);
        }
    }



    public boolean revert(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getBlock() instanceof TickingTorch torchBlock) {
            Block original = QolTorches.getKey(QolTorches.CONVERSION_MAP, torchBlock);

            if (original != null) {
                BlockState newState = original.defaultBlockState();
                if (newState.hasProperty(HorizontalDirectionalBlock.FACING)) {
                    Direction oldFacing = state.getValue(HorizontalDirectionalBlock.FACING);
                    newState = newState.setValue(HorizontalDirectionalBlock.FACING, oldFacing);

                }
                level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE | Block.UPDATE_SUPPRESS_DROPS);
                return true;
            }
        }
        return false;
    }


    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        // Todo optimise this
        ItemPredicate itemCanLight = ItemPredicate.Builder.item().of(
                        level.holderLookup(Registries.ITEM),
                        Items.TORCH, Items.SOUL_TORCH, Items.REDSTONE_TORCH).build();

        if (itemCanLight.test(stack) || stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT)) {
            if (stack.isDamageableItem()) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            }

            if (level instanceof ServerLevel sLevel) {
                if (revert(state, sLevel, pos, level.random)) return InteractionResult.SUCCESS_SERVER;
            }
            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
