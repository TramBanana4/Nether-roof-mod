package com.justagut.NetherRoofMod.entity;


import com.justagut.NetherRoofMod.entity.custom.MagmaHelperEntity;
import com.justagut.NetherRoofMod.netherroofmod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, netherroofmod.MODID);

    public static final Supplier<EntityType<MagmaHelperEntity>> MAGMAHELPER =
            ENTITY_TYPES.register("magmahelper", () -> EntityType.Builder.of(MagmaHelperEntity::new,
                    MobCategory.MONSTER).sized(0.75f, 1.2f).build("magmahelper"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}