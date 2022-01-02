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

public class LargeGary extends PathAwareEntity {

    public LargeGary(EntityType<? extends PathAwareEntity> entityType, World world) {
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
        return 0.5F;
    }

    public float getSoundPitch() {
        return 0.6F;
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
            Gary gary = GaryBox.GARY.create(this.world);
            Gary gary2 = GaryBox.GARY.create(this.world);
            gary.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
            gary2.refreshPositionAndAngles(this.getX()+0.1, this.getY(), this.getZ()+0.1, this.getYaw(), this.getPitch());
            if (this.hasCustomName()) {
                gary.setCustomName(this.getCustomName());
                gary.setCustomNameVisible(this.isCustomNameVisible());
                gary2.setCustomName(this.getCustomName());
                gary2.setCustomNameVisible(this.isCustomNameVisible());
            }
            if (this.isPersistent()) {
                gary.setPersistent();
                gary2.setPersistent();
            }
            gary.setInvulnerable(this.isInvulnerable());
            gary2.setInvulnerable(this.isInvulnerable());
            if(this.random.nextInt(4) == 0) {
                split(gary, gary2);
            }
            for (int itemStack2 = 0; itemStack2 < 3; ++itemStack2) {
                this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX() + this.random.nextDouble() / 2.0, this.getBodyY(1), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
            }
            return ActionResult.success(this.world.isClient);
        } else {
           return super.interactMob(player, hand);
        }
    }
    
    private void split(Entity entity1, Entity entity2) {
        if(!this.world.isClient()) {
            this.world.spawnEntity(entity1);
            this.world.spawnEntity(entity2);
            this.discard();
        }
    }

    protected void initAttributes() {}
}
