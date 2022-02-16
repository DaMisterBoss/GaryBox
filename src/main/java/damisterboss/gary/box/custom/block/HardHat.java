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

public class HardHat extends HorizontalFacingBlock {

    public HardHat(AbstractBlock.Settings settings) {
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
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) { //Unfinished: set for each facing direction
        switch(state.get(FACING)) {
            case NORTH:
              return HAT_A;
            case EAST:
              return HAT_B;
            case SOUTH:
              return HAT_C;
            case WEST:
              return HAT_D;
            default:
              return HAT_A;
        }
    }

    // List of Orientations for the Hard Hat block
    private static final VoxelShape HAT_A = VoxelShapes.union(Block.createCuboidShape(3, 0, 3, 13, 1, 14), Block.createCuboidShape(4, 0, 2, 12, 1, 3), Block.createCuboidShape(4, 1, 5, 12, 5, 13), Block.createCuboidShape(7, 1, 4, 9, 6, 14), Block.createCuboidShape(5, 1, 4, 6, 2, 5), Block.createCuboidShape(10, 1, 4, 11, 2, 5));

    private static final VoxelShape HAT_B = VoxelShapes.union(Block.createCuboidShape(2, 0, 3, 13, 1, 13), Block.createCuboidShape(13, 0, 4, 14, 1, 12), Block.createCuboidShape(3, 1, 4, 11, 5, 12), Block.createCuboidShape(2, 1, 7, 12, 6, 9), Block.createCuboidShape(11, 1, 5, 12, 2, 6), Block.createCuboidShape(11, 1, 10, 12, 2, 11));

    private static final VoxelShape HAT_C = VoxelShapes.union(Block.createCuboidShape(3, 0, 2, 13, 1, 13), Block.createCuboidShape(4, 0, 13, 12, 1, 14), Block.createCuboidShape(4, 1, 3, 12, 5, 11), Block.createCuboidShape(7, 1, 2, 9, 6, 12), Block.createCuboidShape(10, 1, 11, 11, 2, 12), Block.createCuboidShape(5, 1, 11, 6, 2, 12));

    private static final VoxelShape HAT_D = VoxelShapes.union(Block.createCuboidShape(3, 0, 3, 14, 1, 13), Block.createCuboidShape(2, 0, 4, 3, 1, 12), Block.createCuboidShape(5, 1, 4, 13, 5, 12), Block.createCuboidShape(4, 1, 7, 14, 6, 9), Block.createCuboidShape(4, 1, 10, 5, 2, 11), Block.createCuboidShape(4, 1, 5, 5, 2, 6));
}
