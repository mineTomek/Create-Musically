package com.minetomek.musically.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DiscRecorderBlock extends Block implements BlockEntityProvider {
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");
    public static final BooleanProperty FULL = BooleanProperty.of("full");

    public DiscRecorderBlock(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState().with(ACTIVATED, false).with(FULL, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
        builder.add(FULL);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DiscRecorderBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            // Skip if the player isn't allowed to modify the world.
            return ActionResult.PASS;
        } else {
            if (player.getMainHandStack().getName().getString().toLowerCase().contains("red")) {
                // Get the current value of the "full" property
                boolean full = state.get(FULL);

                // Flip the value of activated and save the new blockstate.
                world.setBlockState(pos, state.with(FULL, !full));
            } else {
                // Get the current value of the "activated" property
                boolean activated = state.get(ACTIVATED);

                // Flip the value of activated and save the new blockstate.
                world.setBlockState(pos, state.with(ACTIVATED, !activated));
            }

            // Play a click sound to emphasize the interaction.
            world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return ActionResult.SUCCESS;
        }
    }
}
