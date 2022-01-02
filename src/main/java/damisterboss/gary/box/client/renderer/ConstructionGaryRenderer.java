package damisterboss.gary.box.client.renderer;

import damisterboss.gary.box.GaryBoxClient;
import damisterboss.gary.box.client.model.ConstructionGaryModel;
import damisterboss.gary.box.custom.entity.ConstructionGary;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ConstructionGaryRenderer extends MobEntityRenderer<ConstructionGary, ConstructionGaryModel> {
 
    public ConstructionGaryRenderer(EntityRendererFactory.Context context) {
        super(context, new ConstructionGaryModel(context.getPart(GaryBoxClient.CONSTRUCTION_GARY_LAYER)), 0.5f);
        //float number is shadow radius
    }
 
    @Override
    public Identifier getTexture(ConstructionGary entity) {
        return new Identifier("garybox", "textures/entity/construction_gary/construction_gary.png");
    }
}