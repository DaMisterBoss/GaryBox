package damisterboss.gary.box;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import damisterboss.gary.box.client.model.BusinessGaryModel;
import damisterboss.gary.box.client.model.ConstructionGaryModel;
import damisterboss.gary.box.client.model.GaryModel;
import damisterboss.gary.box.client.model.KingGaryModel;
import damisterboss.gary.box.client.model.LargeGaryModel;
import damisterboss.gary.box.client.model.MedicGaryModel;
import damisterboss.gary.box.client.renderer.BusinessGaryRenderer;
import damisterboss.gary.box.client.renderer.ConstructionGaryRenderer;
import damisterboss.gary.box.client.renderer.GaryRenderer;
import damisterboss.gary.box.client.renderer.HoverGaryRenderer;
import damisterboss.gary.box.client.renderer.KingGaryRenderer;
import damisterboss.gary.box.client.renderer.LargeGaryRenderer;
import damisterboss.gary.box.client.renderer.MedicGaryRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class GaryBoxClient implements ClientModInitializer{
    public static final EntityModelLayer GARY_LAYER = new EntityModelLayer(new Identifier("garybox", "gary"), "main");
    public static final EntityModelLayer LARGE_GARY_LAYER = new EntityModelLayer(new Identifier("garybox", "large_gary"), "main");
    public static final EntityModelLayer BUSINESS_GARY_LAYER = new EntityModelLayer(new Identifier("garybox", "business_gary"), "main");
    public static final EntityModelLayer CONSTRUCTION_GARY_LAYER = new EntityModelLayer(new Identifier("garybox", "construction_gary"), "main");
    public static final EntityModelLayer KING_GARY_LAYER = new EntityModelLayer(new Identifier("garybox", "king_gary"), "main");
    public static final EntityModelLayer HOVER_GARY_LAYER = new EntityModelLayer(new Identifier("garybox", "hover_gary"), "main");
    public static final EntityModelLayer MEDIC_GARY_LAYER = new EntityModelLayer(new Identifier("garybox", "medic_gary"), "main");
    
    @Override
    public void onInitializeClient() {

        //Registers Garys' renderers, which provides a model and a texture for the entity
        EntityRendererRegistry.register(GaryBox.GARY, (context) -> {
            return new GaryRenderer(context);
        });
        
        EntityRendererRegistry.register(GaryBox.LARGE_GARY, (context) -> {
            return new LargeGaryRenderer(context);
        });
        
        EntityRendererRegistry.register(GaryBox.BUSINESS_GARY, (context) -> {
            return new BusinessGaryRenderer(context);
        });
        
        EntityRendererRegistry.register(GaryBox.CONSTRUCTION_GARY, (context) -> {
            return new ConstructionGaryRenderer(context);
        });
        
        EntityRendererRegistry.register(GaryBox.KING_GARY, (context) -> {
            return new KingGaryRenderer(context);
        });
        
        EntityRendererRegistry.register(GaryBox.HOVER_GARY, (context) -> {
            return new HoverGaryRenderer(context);
        });
        
        EntityRendererRegistry.register(GaryBox.MEDIC_GARY, (context) -> {
            return new MedicGaryRenderer(context);
        });
        
        EntityModelLayerRegistry.registerModelLayer(GARY_LAYER, GaryModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(LARGE_GARY_LAYER, LargeGaryModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BUSINESS_GARY_LAYER, BusinessGaryModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CONSTRUCTION_GARY_LAYER, ConstructionGaryModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(KING_GARY_LAYER, KingGaryModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MEDIC_GARY_LAYER, MedicGaryModel::getTexturedModelData);
    
    }
}
