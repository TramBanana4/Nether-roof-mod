package com.justagut.NetherRoofMod.event;


import com.justagut.NetherRoofMod.entity.ModEntities;
import com.justagut.NetherRoofMod.entity.client.MagmaHelperModel;
import com.justagut.NetherRoofMod.entity.custom.MagmaHelperEntity;
import com.justagut.NetherRoofMod.netherroofmod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = netherroofmod.MODID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MagmaHelperModel.LAYER_LOCATION, MagmaHelperModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MAGMAHELPER.get(), MagmaHelperEntity.createAttributes().build());
    }
}