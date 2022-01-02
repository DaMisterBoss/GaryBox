package damisterboss.gary.box.client.renderer;

import damisterboss.gary.box.GaryBoxClient;
import damisterboss.gary.box.client.model.GaryModel;
import damisterboss.gary.box.custom.entity.Gary;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GaryRenderer extends MobEntityRenderer<Gary, GaryModel> {
 
    public GaryRenderer(EntityRendererFactory.Context context) {
        super(context, new GaryModel(context.getPart(GaryBoxClient.GARY_LAYER)), 0.5f);
        //float number is shadow radius
    }
 
    @Override
    public Identifier getTexture(Gary entity) {
        return new Identifier("garybox", "textures/entity/gary/gary.png");
    }
}