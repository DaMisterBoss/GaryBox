package damisterboss.gary.box.custom.block;

import damisterboss.gary.box.GaryBox;
import damisterboss.gary.box.custom.entity.BusinessGary;
import damisterboss.gary.box.custom.entity.ConstructionGary;
import damisterboss.gary.box.custom.entity.Gary;
import damisterboss.gary.box.custom.entity.KingGary;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class OpenGaryBox extends HorizontalFacingBlock {

    public OpenGaryBox(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {

            final VoxelShape BOX_BASE = Block.createCuboidShape(1, 0, 1, 15, 1, 15);
            final VoxelShape BOX_NORTH = Block.createCuboidShape(0, 0, 0, 16, 16, 1);
            final VoxelShape BOX_SOUTH = Block.createCuboidShape(0, 0, 15, 16, 16, 16);
            final VoxelShape BOX_EAST = Block.createCuboidShape(15, 0, 1, 16, 16, 15);
            final VoxelShape BOX_WEST = Block.createCuboidShape(0, 0, 1, 1, 16, 15);
            return VoxelShapes.union(BOX_BASE, new VoxelShape[] {BOX_NORTH, BOX_SOUTH, BOX_EAST, BOX_WEST});

    }

    private LivingEntity livingEntity;
    private ItemStack garyAccessory = ItemStack.EMPTY;

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient)
            return;
        if (isValidGary(entity)) {
            livingEntity = (LivingEntity)entity;
        } else {
            livingEntity = null;
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //detects whether or not livingEntity is within 1 block of the gary box (will only trigger if livingEntity is INSIDE the box)
        world.playSound(null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        if (!world.isClient()) {
            if(livingEntity != null && livingEntity.isAlive() && bound(livingEntity, pos)) {
                livingEntity.discard();
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY() + 1.3D, pos.getZ(), garyAccessory);
                    Direction direction = hit.getSide();
                    Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                    itemEntity.setVelocity(0.05 * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02);
                    world.spawnEntity(itemEntity);
                world.setBlockState(pos, GaryBox.GARY_TYPE_BOX.getDefaultState().with(Properties.HORIZONTAL_FACING, state.get(FACING)));
            } else {
                world.setBlockState(pos, (BlockState)GaryBox.EMPTY_GARY_BOX.getDefaultState().with(Properties.HORIZONTAL_FACING, state.get(FACING)));
            }
        }
        return ActionResult.success(world.isClient);
    }

    //checks whether or not a block and an entity are within a positive 'b' of each other
    //because aparently fabric can't handle just using abs()
    //or I'm just really dumb  
    private Boolean bound(Entity entity, BlockPos pos) {
        if(entity.getBlockPos().compareTo(pos) == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Determines whether the entity in question is a valid gary, and what type box that gary will be put into
    //add new garys here
    private boolean isValidGary(Entity entity) {
        boolean isValid = false;
        if(entity instanceof Gary) {
            isValid = true;
            garyAccessory = ItemStack.EMPTY;
        }
        if(entity instanceof BusinessGary) {
            isValid = true;
            garyAccessory = new ItemStack(GaryBox.TOPHAT, 1);
        }
        if(entity instanceof ConstructionGary) {
            isValid = true;
            garyAccessory = new ItemStack(GaryBox.HARD_HAT, 1);
        }
        if(entity instanceof KingGary) {
            isValid = true;
            garyAccessory = new ItemStack(GaryBox.CROWN, 1);
        }
        return isValid;
    }
}
