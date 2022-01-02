package damisterboss.gary.box.custom.entity;

import java.util.EnumSet;
import javax.annotation.Nullable;

import damisterboss.gary.box.GaryBox;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.annotation.Debug;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class HoverGary extends PathAwareEntity implements IAnimatable, Flutterer {

    private AnimationFactory factory = new AnimationFactory(this);

    public HoverGary(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.lookControl = new HoverGaryLookControl(this);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0f);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0f);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0f);
        this.setPathfindingPenalty(PathNodeType.COCOA, -1.0f);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0f);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).isAir()) {
            return 10.0f;
        }
        return 0.0f;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hover_gary.fly", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.2D));
        this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(GaryBox.GARY_COOKIE), false));
        this.goalSelector.add(4, new HoverGaryWanderAroundGoal());
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    @Debug
    public GoalSelector getGoalSelector() {
        return this.goalSelector;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world){

            @Override
            public boolean isValidPosition(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }

            @Override
            public void tick() {
                super.tick();
            }
        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
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

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.75f;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
    }

    @Override
    public boolean isInAir() {
        return !this.onGround;
    }

    class HoverGaryLookControl
    extends LookControl {
        HoverGaryLookControl(MobEntity entity) {
            super(entity);
        }

        @Override
        public void tick() {
            super.tick();
        }

        @Override
        protected boolean shouldStayHorizontal() {
            return true;
        }
    }

    class HoverGaryWanderAroundGoal
    extends Goal {

        HoverGaryWanderAroundGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return HoverGary.this.navigation.isIdle() && HoverGary.this.random.nextInt(10) == 0;
        }

        @Override
        public boolean shouldContinue() {
            return HoverGary.this.navigation.isFollowingPath();
        }

        @Override
        public void start() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) {
                HoverGary.this.navigation.startMovingAlong(HoverGary.this.navigation.findPathTo(new BlockPos(vec3d), 1), 1.0);
            }
        }

        @Nullable
        private Vec3d getRandomLocation() {
            Vec3d vec3d2;
                vec3d2 = HoverGary.this.getRotationVec(0.0f);
            Vec3d vec3d3 = AboveGroundTargeting.find(HoverGary.this, 8, 7, vec3d2.x, vec3d2.z, 1.5707964f, 3, 1);
            if (vec3d3 != null) {
                return vec3d3;
            }
            return NoPenaltySolidTargeting.find(HoverGary.this, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.SHEARS) {
            Gary gary = GaryBox.GARY.create(this.world);
            gary.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
            if (this.hasCustomName()) {
                gary.setCustomName(this.getCustomName());
                gary.setCustomNameVisible(this.isCustomNameVisible());
            }
            if (this.isPersistent()) {
                gary.setPersistent();
            }
            gary.setInvulnerable(this.isInvulnerable());
            ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), new ItemStack(GaryBox.TOPHAT, 1));
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 1.0F);
            if(!player.getAbilities().creativeMode)
                itemStack.damage(1, random, null);
            for (int itemStack2 = 0; itemStack2 < 3; ++itemStack2) {
                this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX() + this.random.nextDouble() / 2.0, this.getBodyY(1), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
            }
            world.spawnEntity(itemEntity);
            replace(gary);
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

    protected void initAttributes() {}
}
