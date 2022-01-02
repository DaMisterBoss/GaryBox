package damisterboss.gary.box.client.renderer;

import damisterboss.gary.box.GaryBoxClient;
import damisterboss.gary.box.client.model.BusinessGaryModel;
import damisterboss.gary.box.custom.entity.BusinessGary;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BusinessGaryRenderer extends MobEntityRenderer<BusinessGary, BusinessGaryModel> {
 
    public BusinessGaryRenderer(EntityRendererFactory.Context context) {
        super(context, new BusinessGaryModel(context.getPart(GaryBoxClient.BUSINESS_GARY_LAYER)), 0.5f);
        //float number is shadow radius
    }
 
    @Override
    public Identifier getTexture(BusinessGary entity) {
        return new Identifier("garybox", "textures/entity/business_gary/business_gary.png");
    }
}