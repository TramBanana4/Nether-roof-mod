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

public class lucky_block_opener extends Item{
    public lucky_block_opener(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (!level.isClientSide() && clickedBlock == ModBlocks.LUCKY_BLOCK.get()){
            double Random = Math.floor(Math.random()* 8 + 1);
            switch((int)Random){
                case 1: level.setBlockAndUpdate(context.getClickedPos(),Blocks.TNT.defaultBlockState());
                    level.setBlockAndUpdate(context.getClickedPos().above(),Blocks.FIRE.defaultBlockState());
                    break;
                case 2:    level.setBlockAndUpdate(context.getClickedPos(),Blocks.DIAMOND_BLOCK.defaultBlockState());
                    break;
                case 3: level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.BISMUTH_ORE.get().defaultBlockState());
                break;
                case 4: level.setBlockAndUpdate(context.getClickedPos(),Blocks.BEACON.defaultBlockState());
                break;
                case 5: level.setBlockAndUpdate(context.getClickedPos(),Blocks.BEDROCK.defaultBlockState());
                break;
                case 6: level.setBlockAndUpdate(context.getClickedPos(),Blocks.TNT.defaultBlockState());
                    level.setBlockAndUpdate(context.getClickedPos().above(),Blocks.FIRE.defaultBlockState());
                    break;
                case 7: level.setBlockAndUpdate(context.getClickedPos(),Blocks.TNT.defaultBlockState());
                    level.setBlockAndUpdate(context.getClickedPos().above(),Blocks.FIRE.defaultBlockState());
                    break;
                case 8: level.setBlockAndUpdate(context.getClickedPos(),Blocks.TNT.defaultBlockState());
                    level.setBlockAndUpdate(context.getClickedPos().above(),Blocks.FIRE.defaultBlockState());
                    break;
                case 9: level.setBlockAndUpdate(context.getClickedPos(),Blocks.TNT.defaultBlockState());
                    level.setBlockAndUpdate(context.getClickedPos().above(),Blocks.FIRE.defaultBlockState());
                    break;

            }



            context.getItemInHand().hurtAndBreak
                    (1,((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            level.playSound(null,context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        }


        return InteractionResult.SUCCESS;
    }
}