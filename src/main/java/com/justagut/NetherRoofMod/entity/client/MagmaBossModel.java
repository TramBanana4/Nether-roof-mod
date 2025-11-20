package com.justagut.NetherRoofMod.entity.client;

import com.justagut.NetherRoofMod.entity.custom.MagmaBossEntity;
import com.justagut.NetherRoofMod.entity.custom.MagmaHelperEntity;
import com.justagut.NetherRoofMod.netherroofmod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;

public class MagmaBossModel<T extends MagmaBossEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(netherroofmod.MODID, "magmaboss"), "main");
    private final ModelPart body;

    public MagmaBossModel(ModelPart root) {
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition lowerchest = body.addOrReplaceChild("lowerchest", CubeListBuilder.create().texOffs(0, 39).addBox(-13.0F, -19.0F, -8.0F, 26.0F, 19.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

        PartDefinition upperchest = lowerchest.addOrReplaceChild("upperchest", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -23.0F, -9.0F, 30.0F, 21.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, 0.0F));

        PartDefinition head = upperchest.addOrReplaceChild("head", CubeListBuilder.create().texOffs(84, 39).addBox(-7.0F, -14.0F, -8.0F, 14.0F, 13.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(35, 189).addBox(-4.0F, -17.0F, -5.0F, 8.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 0.0F));

        PartDefinition armL1 = upperchest.addOrReplaceChild("armL1", CubeListBuilder.create().texOffs(0, 74).addBox(-18.0F, -7.0F, -10.0F, 18.0F, 9.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, -16.0F, 0.0F));

        PartDefinition armL2 = armL1.addOrReplaceChild("armL2", CubeListBuilder.create().texOffs(0, 103).addBox(-5.0F, 1.0F, -6.0F, 10.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 1.0F, 0.0F));

        PartDefinition armR1 = upperchest.addOrReplaceChild("armR1", CubeListBuilder.create().texOffs(0, 74).addBox(-1.0F, -7.0F, -10.0F, 18.0F, 9.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -16.0F, 0.0F));

        PartDefinition armR2 = armR1.addOrReplaceChild("armR2", CubeListBuilder.create().texOffs(0, 103).addBox(-6.0F, 1.0F, -6.0F, 10.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 1.0F, 0.0F));

        PartDefinition legL = body.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(88, 103).addBox(-4.0F, 3.0F, -5.0F, 8.0F, 21.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -24.0F, 0.0F));

        PartDefinition lagR = body.addOrReplaceChild("lagR", CubeListBuilder.create().texOffs(96, 0).addBox(-4.0F, 3.0F, -5.0F, 8.0F, 21.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(MagmaBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);


        this.animate(entity.idleAnimationState,MagmaBossAnimations.IDLE, ageInTicks, 1f);
        this.animateWalk(MagmaBossAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay,color);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}

