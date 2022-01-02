package damisterboss.gary.box.client.model;

import com.google.common.collect.ImmutableList;

import damisterboss.gary.box.custom.entity.ConstructionGary;
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

public class ConstructionGaryModel extends EntityModel<ConstructionGary> {
    
    private final ModelPart base;
    private final ModelPart hat;
    private final ModelPart belt;

    public ConstructionGaryModel(ModelPart modelPart) {
        this.base = modelPart.getChild(EntityModelPartNames.CUBE);
        this.hat = modelPart.getChild(EntityModelPartNames.HAT);
        this.belt = modelPart.getChild(EntityModelPartNames.JACKET);
        setRotationAngle(hat, belt, 0, 0, 0);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        float n = 12;
        float m = 24;
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F), ModelTransform.pivot(0F, 0F, 0F));
        modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create()
            .uv(36, 0).cuboid(-5.0F, -13.0F + n, -6.0F, 10.0F, 1.0F, 11.0F)
            .uv(24, 39).cuboid(-4.0F, -13.0F + n, -7.0F, 8.0F, 1.0F, 1.0F)
            .uv(0, 39).cuboid(-4.0F, -17.0F + n, -4.0F, 8.0F, 4.0F, 8.0F)
            .uv(39, 14).cuboid(-1.0F, -18.0F + n, -5.0F, 2.0F, 5.0F, 10.0F)
            .uv(6, 0).cuboid(2.0F, -14.0F + n, -5.0F, 1.0F, 1.0F, 1.0F)
            .uv(0, 0).cuboid(-3.0F, -14.0F + n, -5.0F, 1.0F, 1.0F, 1.0F), 
            ModelTransform.pivot(0.0F, 0.0F + n, 0.0F));
        modelPartData.addChild(EntityModelPartNames.JACKET, ModelPartBuilder.create()
            .uv(0, 24).cuboid(-6.5F, -3.0F + m, -6.5F, 13.0F, 2.0F, 13.0F)
            .uv(0, 24).cuboid(6.0F, -5.0F + m, -3.0F, 1.0F, 5.0F, 1.0F)
            .uv(0, 7).cuboid(5.5F, -7.0F + m, -4.0F, 2.0F, 2.0F, 3.0F)
            .uv(0, 0).cuboid(-7.0F, -3.5F + m, -3.0F, 1.0F, 3.0F, 4.0F)
            .uv(7, 7).cuboid(-7.0F, -1.5F + m, -4.0F, 1.0F, 1.0F, 1.0F)
            , ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(ConstructionGary entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.base).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        ImmutableList.of(this.hat).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        ImmutableList.of(this.belt).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelPart hat, ModelPart belt, float x, float y, float z) {
        hat.pitch = x;
        hat.yaw = y;
        hat.roll = z;
        belt.pitch = x;
        belt.yaw = y;
        belt.roll = z;
    }
}
