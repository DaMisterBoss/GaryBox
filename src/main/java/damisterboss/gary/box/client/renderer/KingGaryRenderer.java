package damisterboss.gary.box.client.renderer;

import damisterboss.gary.box.GaryBoxClient;
import damisterboss.gary.box.client.model.KingGaryModel;
import damisterboss.gary.box.custom.entity.KingGary;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class KingGaryRenderer extends MobEntityRenderer<KingGary, KingGaryModel> {
 
    public KingGaryRenderer(EntityRendererFactory.Context context) {
        super(context, new KingGaryModel(context.getPart(GaryBoxClient.KING_GARY_LAYER)), 0.5f);
        //float number is shadow radius
    }
 
    @Override
    public Identifier getTexture(KingGary entity) {
        return new Identifier("garybox", "textures/entity/king_gary/king_gary.png");
    }
}