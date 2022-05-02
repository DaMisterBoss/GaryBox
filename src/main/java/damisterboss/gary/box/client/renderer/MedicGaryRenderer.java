package damisterboss.gary.box.client.renderer;

import damisterboss.gary.box.GaryBoxClient;
import damisterboss.gary.box.client.model.MedicGaryModel;
import damisterboss.gary.box.custom.entity.MedicGary;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MedicGaryRenderer extends MobEntityRenderer<MedicGary, MedicGaryModel> {
 
    public MedicGaryRenderer(EntityRendererFactory.Context context) {
        super(context, new MedicGaryModel(context.getPart(GaryBoxClient.MEDIC_GARY_LAYER)), 0.5f);
        //float number is shadow radius
    }
 
    @Override
    public Identifier getTexture(MedicGary entity) {
        return new Identifier("garybox", "textures/entity/medic_gary/medic_gary.png");
    }
}