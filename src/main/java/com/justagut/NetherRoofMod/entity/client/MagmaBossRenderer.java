package com.justagut.NetherRoofMod.entity.client;

import com.justagut.NetherRoofMod.entity.custom.MagmaBossEntity;
import com.justagut.NetherRoofMod.netherroofmod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MagmaBossRenderer extends MobRenderer<MagmaBossEntity,MagmaBossModel<MagmaBossEntity>> {
    public MagmaBossRenderer(EntityRendererProvider.Context context) {
        super(context, new MagmaBossModel<>(context.bakeLayer(MagmaBossModel.LAYER_LOCATION)),1f);
    }

    @Override
    public ResourceLocation getTextureLocation(MagmaBossEntity magmaBossEntity) {
        return ResourceLocation.fromNamespaceAndPath(netherroofmod.MODID,"textures/entity/magma_boss_texture.png");
    }

    @Override
    public void render(MagmaBossEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
