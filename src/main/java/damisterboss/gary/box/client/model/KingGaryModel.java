package damisterboss.gary.box.client.model;

import com.google.common.collect.ImmutableList;

import damisterboss.gary.box.custom.entity.KingGary;
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

public class KingGaryModel extends EntityModel<KingGary> {
    
    private final ModelPart base;
    private final ModelPart crown;

    public KingGaryModel(ModelPart modelPart) {
        this.base = modelPart.getChild(EntityModelPartNames.CUBE);
        this.crown = modelPart.getChild(EntityModelPartNames.HAT);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F), ModelTransform.pivot(0F, 0F, 0F));
        modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create()
            .uv(8, 24).cuboid(2.0F, -15.0F, -3.0F, 1.0F, 3.0F, 1.0F)
            .uv(19, 27).cuboid(1.0F, -14.0F, -3.0F, 1.0F, 2.0F, 1.0F)
            .uv(6, 8).cuboid(-1.0F, -15.0F, -3.0F, 2.0F, 3.0F, 1.0F)
            .uv(15, 27).cuboid(-2.0F, -14.0F, -3.0F, 1.0F, 2.0F, 1.0F)
            .uv(4, 24).cuboid(-3.0F, -15.0F, -3.0F, 1.0F, 3.0F, 1.0F)
            .uv(11, 27).cuboid(-3.0F, -14.0F, -2.0F, 1.0F, 2.0F, 1.0F)
            .uv(4, 3).cuboid(-3.0F, -15.0F, -1.0F, 1.0F, 3.0F, 2.0F)
            .uv(24, 24).cuboid(-3.0F, -14.0F, 1.0F, 1.0F, 2.0F, 1.0F)
            .uv(0, 24).cuboid(-3.0F, -15.0F, 2.0F, 1.0F, 3.0F, 1.0F)
            .uv(0, 8).cuboid(-1.0F, -15.0F, 2.0F, 2.0F, 3.0F, 1.0F)
            .uv(20, 24).cuboid(1.0F, -14.0F, 2.0F, 1.0F, 2.0F, 1.0F)
            .uv(8, 0).cuboid(2.0F, -15.0F, 2.0F, 1.0F, 3.0F, 1.0F)
            .uv(16, 24).cuboid(2.0F, -14.0F, 1.0F, 1.0F, 2.0F, 1.0F)
            .uv(0, 0).cuboid(2.0F, -15.0F, -1.0F, 1.0F, 3.0F, 2.0F)
            .uv(12, 24).cuboid(2.0F, -14.0F, -2.0F, 1.0F, 2.0F, 1.0F)
            .uv(0, 5).cuboid(-2.0F, -14.0F, 2.0F, 1.0F, 2.0F, 1.0F),
            ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(KingGary entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.base).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        ImmutableList.of(this.crown).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }
}
