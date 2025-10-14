package com.justagut.NetherRoofMod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
    CompoundTag count = new CompoundTag();

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        BlockPos bedpos = context.getClickedPos();
        BlockPos airpos = context.getClickedPos().relative(context.getClickedFace());

        if (!level.isClientSide()) {
            if (clickedBlock == Blocks.BEDROCK && count.getDouble("bedrock") == 0) {
                level.setBlockAndUpdate(bedpos, Blocks.AIR.defaultBlockState());
                count.putDouble("bedrock", 1);
            } else {
                if (level.isEmptyBlock(airpos) && count.getDouble("bedrock") == 1) {
                    level.setBlockAndUpdate(airpos, Blocks.BEDROCK.defaultBlockState());
                    count.putDouble("bedrock", 0);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}