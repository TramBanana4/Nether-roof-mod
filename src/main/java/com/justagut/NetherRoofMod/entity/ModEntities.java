package com.justagut.NetherRoofMod.entity;

import com.justagut.NetherRoofMod.entity.custom.MagmaBoss;
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

    public static final Supplier<EntityType<MagmaBoss>> MAGMABOSS =
            ENTITY_TYPES.register("magmaboss", () -> EntityType.Builder.of(MagmaBoss::new, MobCategory.MONSTER)
                    .sized(0.75f, 0.35f).build("magmaboss"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}