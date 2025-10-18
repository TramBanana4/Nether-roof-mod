package com.justagut.NetherRoofMod.entity.client;


import com.justagut.NetherRoofMod.entity.custom.MagmaHelperEntity;
import com.justagut.NetherRoofMod.netherroofmod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class magmahelperRenderer extends MobRenderer<MagmaHelperEntity, MagmaHelperModel<MagmaHelperEntity>> {
        public magmahelperRenderer(EntityRendererProvider.Context context) {
            super(context, new MagmaHelperModel<>(context.bakeLayer(MagmaHelperModel.LAYER_LOCATION)), 0.25f);
        }

        @Override
        public ResourceLocation getTextureLocation(MagmaHelperEntity entity) {
            return ResourceLocation.fromNamespaceAndPath(netherroofmod.MODID, "textures/entity/magma_helper_texture.png");
        }

        @Override
        public void render(MagmaHelperEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
            if(entity.isBaby()) {
                poseStack.scale(0.45f, 0.45f, 0.45f);
            } else {
                poseStack.scale(1f, 1f, 1f);
            }

            super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }
    }

