package damisterboss.gary.box.client.renderer;

import damisterboss.gary.box.GaryBoxClient;
import damisterboss.gary.box.client.model.LargeGaryModel;
import damisterboss.gary.box.custom.entity.LargeGary;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class LargeGaryRenderer extends MobEntityRenderer<LargeGary, LargeGaryModel> {
 
    public LargeGaryRenderer(EntityRendererFactory.Context context) {
        super(context, new LargeGaryModel(context.getPart(GaryBoxClient.LARGE_GARY_LAYER)), 0.6f);
    }
 
    @Override
    public Identifier getTexture(LargeGary entity) {
        return new Identifier("garybox", "textures/entity/large_gary/large_gary.png");
    }
}