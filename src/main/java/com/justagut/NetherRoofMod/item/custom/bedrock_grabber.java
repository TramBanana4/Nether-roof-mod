package com.justagut.NetherRoofMod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class bedrock_grabber extends Item{
    public bedrock_grabber(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        BlockPos bedpos = context.getClickedPos();
        BlockPos airpos = context.getClickedPos().relative(context.getClickedFace());

        if (!level.isClientSide()) {
            if (clickedBlock == Blocks.BEDROCK) {
                level.setBlockAndUpdate(bedpos, Blocks.AIR.defaultBlockState());
            } else {
                if (level.isEmptyBlock(airpos)) {
                    BlockState state = Blocks.BEDROCK.defaultBlockState();
                    level.setBlockAndUpdate(airpos, state);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}