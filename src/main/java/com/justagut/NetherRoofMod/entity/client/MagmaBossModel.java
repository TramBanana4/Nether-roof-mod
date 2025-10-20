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

public class MagmaBossModel<T extends MagmaBossEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(netherroofmod.MODID, "magmaboss"), "main");
    public final ModelPart body;

    public MagmaBossModel(ModelPart root) {
        this.body = root.getChild("body");

    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 11).addBox(-6.0F, -14.0F, -2.0F, 12.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone2 = body.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(4.0F, -6.0F, 1.0F));

        PartDefinition cube_r1 = bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.587F, -6.8422F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.45F, -0.575F, -0.5F, 3.1416F, 0.0F, -0.1309F));

        PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-4.0F, -6.0F, 1.0F));

        PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.75F, -0.5F, 0.0F, 1.5708F, 0.1309F));

        PartDefinition bone3 = body.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -5.0F, -3.5F, 8.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(MagmaBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);


        this.animateWalk(MagmaBossAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}

