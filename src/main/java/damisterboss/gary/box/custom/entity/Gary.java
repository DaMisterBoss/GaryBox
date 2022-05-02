package damisterboss.gary.box.custom.entity;

import damisterboss.gary.box.GaryBox;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Gary extends PathAwareEntity {

    private Entity typeGary;

    public Gary(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.2D));
        this.goalSelector.add(2, new TemptGoal(this, 1.25D, Ingredient.ofItems(GaryBox.GARY_COOKIE), false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    protected SoundEvent getAmbientSound() {
        return GaryBox.GARY_AMBIENT_EVENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return GaryBox.GARY_HURT_EVENT;
    }

    protected SoundEvent getDeathSound() {
        return GaryBox.GARY_DEATH_EVENT;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.BLOCK_SHROOMLIGHT_STEP, 0.10F, 1.0F);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.GLASS_BOTTLE) {
           player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
           ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, GaryBox.GARY_SAUCE.getDefaultStack());
           player.setStackInHand(hand, itemStack2);
           return ActionResult.success(this.world.isClient);
        } else if (itemStack.getItem() == GaryBox.GARY_COOKIE) {
            player.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
            itemStack.decrement(1);
            LargeGary largeGary = GaryBox.LARGE_GARY.create(this.world);
            largeGary.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
            if (this.hasCustomName()) {
                largeGary.setCustomName(this.getCustomName());
                largeGary.setCustomNameVisible(this.isCustomNameVisible());
            }
            if (this.isPersistent()) {
                largeGary.setPersistent();
            }
            largeGary.setInvulnerable(this.isInvulnerable());
            if(this.random.nextInt(3) == 0) {
                replace(largeGary);
            }
            for (int itemStack2 = 0; itemStack2 < 3; ++itemStack2) {
                this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX() + this.random.nextDouble() / 2.0, this.getBodyY(1), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
            }
            return ActionResult.success(this.world.isClient);
        } else if (getGaryType(itemStack)) {
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 1.0F);
            itemStack.decrement(1);
            Entity entity = typeGary;
            typeGary.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
            if (this.hasCustomName()) {
                entity.setCustomName(this.getCustomName());
                entity.setCustomNameVisible(this.isCustomNameVisible());
            }
            if (this.isPersistent()) {
                ((MobEntity) entity).setPersistent();
            }
            entity.setInvulnerable(this.isInvulnerable());
            replace(entity);
            for (int itemStack2 = 0; itemStack2 < 3; ++itemStack2) {
                this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX() + this.random.nextDouble() / 2.0, this.getBodyY(1), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
            }
            return ActionResult.success(this.world.isClient);
        } else {
           return super.interactMob(player, hand);
        }
    }

    private void replace(Entity entity) {
        if(!this.world.isClient()) {
            this.world.spawnEntity(entity);
            this.discard();
        }
    }

    private boolean getGaryType(ItemStack item) {
        boolean isValid = false;
        if(item.getItem() == new ItemStack(GaryBox.TOPHAT).getItem()) {
            isValid = true;
            typeGary = GaryBox.BUSINESS_GARY.create(this.world);
        }
        if(item.getItem() == new ItemStack(GaryBox.HARD_HAT).getItem()) {
            isValid = true;
            typeGary = GaryBox.CONSTRUCTION_GARY.create(this.world);
        }
        if(item.getItem() == new ItemStack(GaryBox.CROWN).getItem()) {
            isValid = true;
            typeGary = GaryBox.KING_GARY.create(this.world);
        }
        if(item.getItem() == new ItemStack(Items.GOLDEN_APPLE).getItem()) {
            isValid = true;
            typeGary = GaryBox.MEDIC_GARY.create(this.world);
        }
        if(item.getItem() == new ItemStack(GaryBox.CYBERNETICS).getItem()) {
            isValid = true;
            typeGary = GaryBox.HOVER_GARY.create(this.world);
        }
        return isValid;
    }

    protected void initAttributes() {}
}
