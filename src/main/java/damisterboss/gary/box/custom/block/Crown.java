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

public class Crown extends HorizontalFacingBlock {

    public Crown(AbstractBlock.Settings settings) {
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
        return VoxelShapes.union(
            Block.createCuboidShape(5, 0, 5, 6, 3, 6),
            Block.createCuboidShape(6, 0, 5, 7, 2, 6),
            Block.createCuboidShape(7, 0, 5, 9, 3, 6),
            Block.createCuboidShape(9, 0, 5, 10, 2, 6),
            Block.createCuboidShape(10, 0, 5, 11, 3, 6),
            Block.createCuboidShape(10, 0, 6, 11, 2, 7),
            Block.createCuboidShape(10, 0, 7, 11, 3, 9),
            Block.createCuboidShape(10, 0, 9, 11, 2, 10),
            Block.createCuboidShape(10, 0, 10, 11, 3, 11),
            Block.createCuboidShape(7, 0, 10, 9, 3, 11),
            Block.createCuboidShape(6, 0, 10, 7, 2, 11),
            Block.createCuboidShape(5, 0, 10, 6, 3, 11),
            Block.createCuboidShape(5, 0, 9, 6, 2, 10),
            Block.createCuboidShape(5, 0, 7, 6, 3, 9),
            Block.createCuboidShape(5, 0, 6, 6, 2, 7),
            Block.createCuboidShape(9, 0, 10, 10, 2, 11)
        );
    }
}
