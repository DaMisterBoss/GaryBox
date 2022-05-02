package damisterboss.gary.box.custom.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class GaryFoodComponents {

    public static final FoodComponent GARY_COOKIE = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent GARY_SAUCE = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.1f).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 60, 0), 1).alwaysEdible().build();
    public static final FoodComponent GARY_MATTER = (new FoodComponent.Builder()).hunger(0).saturationModifier(0.5f).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 60, 0), 1).alwaysEdible().build();
    
}
