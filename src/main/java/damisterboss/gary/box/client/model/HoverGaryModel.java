package damisterboss.gary.box.client.model;

import damisterboss.gary.box.custom.entity.HoverGary;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HoverGaryModel extends AnimatedGeoModel<HoverGary> {

    @Override
    public Identifier getModelLocation(HoverGary object)
    {
        return new Identifier("garybox", "geo/hover_gary.geo.json");
    }

    @Override
    public Identifier getTextureLocation(HoverGary object)
    {
        return new Identifier("garybox", "textures/entity/hover_gary/hover_gary.png");
    }

    @Override
    public Identifier getAnimationFileLocation(HoverGary object)
    {
        return new Identifier("garybox", "animations/animation.hover_gary.fly.json");
    }
}
