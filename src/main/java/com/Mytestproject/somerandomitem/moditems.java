package com.Mytestproject.somerandomitem;

import com.Mytestproject.somerandomitem.custom.brickifier;
import com.Mytestproject.somerandomitem.custom.lucky_block_opener;
import com.Mytestproject.testproject;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class moditems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(testproject.MODID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register
            ("bismuth", ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register
            ("raw_bismuth", ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRICKIFIER = ITEMS.register
            ("brickifier", ()-> new brickifier(new Item.Properties().durability(32)));
    public static final DeferredItem<Item> LUCKY_BLOCK_OPENER = ITEMS.register
            ("lucky_block_opener", ()-> new lucky_block_opener(new Item.Properties().durability(8)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

