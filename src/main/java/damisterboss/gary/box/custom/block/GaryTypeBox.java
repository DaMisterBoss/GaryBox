package damisterboss.gary.box.custom.block;

import damisterboss.gary.box.GaryBox;
import damisterboss.gary.box.custom.entity.Gary;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GaryTypeBox extends HorizontalFacingBlock {

    public GaryTypeBox(Settings settings) {
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

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient() && !hasBlockOnTop(world, pos)) {
            world.playSound(null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            Gary gary = GaryBox.GARY.create(world);
            gary.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            gary.setVelocity(new Vec3d(0, 0.5, 0));
            world.setBlockState(pos, (BlockState)GaryBox.OPEN_GARY_BOX.getDefaultState().with(Properties.HORIZONTAL_FACING, state.get(FACING)));
            world.spawnEntity(gary);
            gary = null;
            for (int itemStack2 = 0; itemStack2 < 3; ++itemStack2) {
                world.addParticle(ParticleTypes.INSTANT_EFFECT, pos.getX() + world.random.nextDouble() / 2.0, pos.getY(), pos.getZ() + world.random.nextDouble() / 2.0, world.random.nextDouble() / 3.0, world.random.nextDouble(), world.random.nextDouble() / 3.0);
            }
        }
        return ActionResult.success(world.isClient);
    }
  
    private static boolean hasBlockOnTop(BlockView world, BlockPos pos) {
      BlockPos blockPos = pos.up();
      return !world.getBlockState(blockPos).isAir();
    }
}
