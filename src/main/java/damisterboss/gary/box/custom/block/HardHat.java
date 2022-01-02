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
        final VoxelShape RIM = Block.createCuboidShape(3, 0, 3, 13, 1, 14);
        final VoxelShape BILL = Block.createCuboidShape(4, 0, 2, 12, 1, 3);
        final VoxelShape DOME = Block.createCuboidShape(4, 1, 5, 12, 5, 13);
        final VoxelShape CREST = Block.createCuboidShape(7, 1, 4, 9, 6, 14);
        final VoxelShape NUBL = Block.createCuboidShape(5, 1, 4, 6, 2, 5);
        final VoxelShape NUBR = Block.createCuboidShape(10, 1, 4, 11, 2, 5);
        return VoxelShapes.union(RIM, BILL, DOME, CREST, NUBL, NUBR);
    }
}
