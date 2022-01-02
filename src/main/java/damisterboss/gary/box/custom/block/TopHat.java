package damisterboss.gary.box.custom.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class TopHat extends HorizontalFacingBlock {

    public TopHat(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
  
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      BlockPos blockPos = pos.down();
      return (hasTopRim((BlockView)world, blockPos) || sideCoversSmallSquare(world, blockPos, Direction.UP));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final VoxelShape HAT = Block.createCuboidShape(6, 1, 6, 10, 4, 10);
        final VoxelShape HAT_RIM = Block.createCuboidShape(5, 0, 5, 11, 1, 11);
        return VoxelShapes.union(HAT, HAT_RIM);
    }
}
