package com.justagut.NetherRoofMod.item;

import com.justagut.NetherRoofMod.item.custom.bedrock_grabber;
import com.justagut.NetherRoofMod.item.custom.other_bedrock_grabber;
import com.justagut.NetherRoofMod.netherroofmod;
import com.justagut.NetherRoofMod.item.custom.brickifier;
import com.justagut.NetherRoofMod.item.custom.lucky_block_opener;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(netherroofmod.MODID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register
            ("bismuth", ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register
            ("raw_bismuth", ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRICKIFIER = ITEMS.register
            ("brickifier", ()-> new brickifier(new Item.Properties().durability(32)));
    public static final DeferredItem<Item> LUCKY_BLOCK_OPENER = ITEMS.register
            ("lucky_block_opener", ()-> new lucky_block_opener(new Item.Properties().durability(8)));
    //replace later with advanced item
    public static final DeferredItem<Item> BEDROCK_GRABBER = ITEMS.register
            ("bedrock_grabber", ()-> new other_bedrock_grabber(new Item.Properties().durability(10)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

