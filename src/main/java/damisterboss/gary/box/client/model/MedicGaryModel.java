package damisterboss.gary.box.client.model;

import com.google.common.collect.ImmutableList;

import damisterboss.gary.box.custom.entity.MedicGary;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;

public class MedicGaryModel extends EntityModel<MedicGary> {
    
    private final ModelPart base;
    private final ModelPart syringe;

    public MedicGaryModel(ModelPart modelPart) {
        this.base = modelPart.getChild(EntityModelPartNames.CUBE);
        this.syringe = modelPart.getChild(EntityModelPartNames.ARMS);
        setRotationAngle(syringe, 0, 0, 0);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        float n = 12;
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.ARMS, ModelPartBuilder.create()
            .uv(0, 24).cuboid(-1.0F, 3.0F + n, -1.0F, 2.0F, 1.0F, 2.0F)
            .uv(0, 4).cuboid(-1.0F, -4.0F + n, -1.0F, 2.0F, 5.0F, 2.0F)
            .uv(8, 24).cuboid(-0.5F, 2.0F + n, -0.5F, 1.0F, 1.0F, 1.0F)
            .uv(12, 24).cuboid(-0.5F, -7.0F + n, -0.5F, 1.0F, 3.0F, 1.0F)
            .uv(0, 0).cuboid(-1.5F, 1.0F + n, -1.5F, 3.0F, 1.0F, 3.0F), 
            ModelTransform.pivot(8.0F, 7.0F, 0.0F));
            return TexturedModelData.of(modelData, 64, 64);
            /*PartDefinition syringe = partdefinition.addOrReplaceChild("syringe", CubeListBuilder.create()
            .texOffs().addBox(, new CubeDeformation(0.0F))
            .texOffs().addBox(, new CubeDeformation(0.0F))
            .texOffs().addBox(, new CubeDeformation(0.0F))
            .texOffs().addBox(, new CubeDeformation(0.0F))
            .texOffs().addBox(, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 20.0F, -0.5F, , 0.0F, 0.0F));\

            PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(, new CubeDeformation(0.0F)), PartPose.offset());
            */
    }

    @Override
    public void setAngles(MedicGary entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.base).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        ImmutableList.of(this.syringe).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelPart syringe, float x, float y, float z) {
        syringe.pitch = x;
        syringe.yaw = y;
        syringe.roll = z;
    }
}
