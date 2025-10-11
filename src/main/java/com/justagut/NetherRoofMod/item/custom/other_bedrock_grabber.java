package com.justagut.NetherRoofMod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class other_bedrock_grabber extends Item{
    private static final Map<Block,Block> BRICK_MAP =
            Map.of(
                    Blocks.BEDROCK, Blocks.AIR,
                    Blocks.AIR, Blocks.BEDROCK

            );
    public other_bedrock_grabber(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (!level.isClientSide()){
            level.setBlockAndUpdate(context.getClickedPos(),BRICK_MAP.get(clickedBlock).defaultBlockState());
            context.getItemInHand().hurtAndBreak
                    (1,((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            level.playSound(null,context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        }


        return InteractionResult.SUCCESS;
    }
}