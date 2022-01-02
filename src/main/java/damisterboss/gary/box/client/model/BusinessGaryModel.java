package damisterboss.gary.box.client.model;

import com.google.common.collect.ImmutableList;

import damisterboss.gary.box.custom.entity.BusinessGary;
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

public class BusinessGaryModel extends EntityModel<BusinessGary> {
    
    private final ModelPart base;
    private final ModelPart hat;
    private final ModelPart hatRim;

    public BusinessGaryModel(ModelPart modelPart) {
        this.base = modelPart.getChild(EntityModelPartNames.CUBE);
        this.hat = modelPart.getChild(EntityModelPartNames.HAT);
        this.hatRim = modelPart.getChild(EntityModelPartNames.HAT_RIM);
        setRotationAngle(hat, hatRim, 0.2618F, 0.6545F, 0.4363F);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F), ModelTransform.pivot(0F, 0F, 0F));
        modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(24, 24).cuboid(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F), ModelTransform.pivot(5.0F, 12.0F, -5.0F));
        modelPartData.addChild(EntityModelPartNames.HAT_RIM, ModelPartBuilder.create().uv(0, 24).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 6.0F), ModelTransform.pivot(5.0F, 12.0F, -5.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(BusinessGary entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.base).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        ImmutableList.of(this.hat).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        ImmutableList.of(this.hatRim).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelPart hat, ModelPart hatRim, float x, float y, float z) {
        hat.pitch = x;
        hat.yaw = y;
        hat.roll = z;
        hatRim.pitch = x;
        hatRim.yaw = y;
        hatRim.roll = z;
    }
}
