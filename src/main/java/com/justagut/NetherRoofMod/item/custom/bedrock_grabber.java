package com.justagut.NetherRoofMod.item.custom;

import com.justagut.NetherRoofMod.block.ModBlocks;
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
import org.jetbrains.annotations.NotNull;

public class bedrock_grabber extends Item{
    public bedrock_grabber(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (!level.isClientSide() && clickedBlock == Blocks.BEDROCK){
            level.setBlockAndUpdate(context.getClickedPos(),Blocks.AIR.defaultBlockState());
            } else if (!level.isClientSide() && clickedBlock ==Blocks.AIR){
            level.setBlockAndUpdate(context.getClickedPos(),Blocks.BEDROCK.defaultBlockState());
        }


        context.getItemInHand().hurtAndBreak
                    (1,((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            level.playSound(null,context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

        return InteractionResult.SUCCESS;
        }
}