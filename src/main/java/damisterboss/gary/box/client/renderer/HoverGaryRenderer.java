package damisterboss.gary.box.client.renderer;

import damisterboss.gary.box.client.model.HoverGaryModel;
import damisterboss.gary.box.custom.entity.HoverGary;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HoverGaryRenderer extends GeoEntityRenderer<HoverGary> {
    
    public HoverGaryRenderer(EntityRendererFactory.Context context) {
        super(context, new HoverGaryModel());
            this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
    }

    // Makes minecraft render the HoverGary entity respecting any transparent pixels present in the entity texture
    // The overrided method below should be all you need to get a translucent entity
    @Override
    public RenderLayer getRenderType(HoverGary animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(GeoModel model, HoverGary animatable, float partialTicks, RenderLayer type, MatrixStack matrixStackIn, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}

// you have no idea how hard it was for me to find out how to get an entity's texture to render as translucent... this code represents more effort than was put in to make the hovergary animation work in the first place
// fml, ilml
// i regret nothing