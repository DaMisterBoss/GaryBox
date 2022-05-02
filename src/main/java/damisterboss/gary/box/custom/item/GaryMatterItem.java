package damisterboss.gary.box.custom.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;

public class GaryMatterItem extends Item {
    
    public GaryMatterItem(Item.Settings settings) {
        super(settings);
    }
  
    public SoundEvent getDrinkSound() {
      return SoundEvents.ENTITY_GENERIC_DRINK;
    }
    
    public SoundEvent getEatSound() {
      return SoundEvents.BLOCK_GRASS_STEP;
    }
  
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
