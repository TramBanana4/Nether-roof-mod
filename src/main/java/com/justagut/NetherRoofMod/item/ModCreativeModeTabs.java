package com.justagut.NetherRoofMod.item;

import com.justagut.NetherRoofMod.block.ModBlocks;
import com.justagut.NetherRoofMod.netherroofmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, netherroofmod.MODID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB =
            CREATIVE_MODE_TAB.register("bismuth_items_tab", ()-> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(ModItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.netherroofmod.bismuth_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);
                        output.accept(ModItems.BRICKIFIER);
                        output.accept(ModItems.LUCKY_BLOCK_OPENER);
                        output.accept(ModItems.BEDROCK_GRABBER);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> BISMUTH_BLOCKS_TAB =
            CREATIVE_MODE_TAB.register("bismuth_blocks_tab", ()-> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(netherroofmod.MODID, "bismuth_items_tab"))
                    .icon(()-> new ItemStack(ModBlocks.GOLD_BLACKSTONE_BRICKS.get()))
                    .title(Component.translatable("creativetab.netherroofmod.bismuth_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.GOLD_BLACKSTONE_BRICKS);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                        output.accept(ModBlocks.LUCKY_BLOCK);
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
