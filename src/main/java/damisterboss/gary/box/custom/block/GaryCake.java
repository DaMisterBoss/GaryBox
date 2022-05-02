package damisterboss.gary.box.custom.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class GaryCake extends Block {
  public static final int MAX_BITES = 6;
  
  public static final IntProperty BITES = Properties.BITES;
  
  public static final int DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
  
  protected static final float field_31047 = 1.0F;
  
  protected static final float field_31048 = 2.0F;
  
  protected static final VoxelShape[] BITES_TO_SHAPE = new VoxelShape[] { Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
      Block.createCuboidShape(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
      Block.createCuboidShape(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
      Block.createCuboidShape(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
      Block.createCuboidShape(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
      Block.createCuboidShape(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
      Block.createCuboidShape(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D) };
  
  public GaryCake(AbstractBlock.Settings settings) {
    super(settings);
    setDefaultState((this.stateManager.getDefaultState()).with(BITES, Integer.valueOf(0)));
  }
  
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return BITES_TO_SHAPE[(state.get(BITES)).intValue()];
  }
  
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    ItemStack itemStack = player.getStackInHand(hand);
    Item item = itemStack.getItem();
    if (itemStack.isIn(ItemTags.CANDLES) && ((Integer)state.get(BITES)).intValue() == 0) {
      Block block = Block.getBlockFromItem(item);
      if (block instanceof CandleBlock) {
        if (!player.isCreative())
          itemStack.decrement(1); 
        world.playSound(null, pos, SoundEvents.BLOCK_CAKE_ADD_CANDLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.setBlockState(pos, CandleCakeBlock.getCandleCakeFromCandle(block));
        world.emitGameEvent((Entity)player, GameEvent.BLOCK_CHANGE, pos);
        player.incrementStat(Stats.USED.getOrCreateStat(item));
        return ActionResult.SUCCESS;
      } 
    } 
    if (world.isClient) {
      if (tryEat((WorldAccess)world, pos, state, player).isAccepted())
        return ActionResult.SUCCESS; 
      if (itemStack.isEmpty())
        return ActionResult.CONSUME; 
    } 
    return tryEat((WorldAccess)world, pos, state, player);
  }
  
  protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
    if (!player.canConsume(false))
      return ActionResult.PASS; 
    player.incrementStat(Stats.EAT_CAKE_SLICE);
    player.getHungerManager().add(2, 0.1F);
    int i = (state.get(BITES)).intValue();
    world.emitGameEvent((Entity)player, GameEvent.EAT, pos);
    if (i < 6) {
      world.setBlockState(pos, state.with(BITES, Integer.valueOf(i + 1)), 3);
    } else {
      world.removeBlock(pos, false);
      world.emitGameEvent((Entity)player, GameEvent.BLOCK_DESTROY, pos);
    } 
    return ActionResult.SUCCESS;
  }
  
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    return world.getBlockState(pos.down()).getMaterial().isSolid();
  }
  
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(new Property[] { BITES });
  }
  
  public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
    return getComparatorOutput((state.get(BITES)).intValue());
  }
  
  public static int getComparatorOutput(int bites) {
    return (7 - bites) * 2;
  }
  
  public boolean hasComparatorOutput(BlockState state) {
    return true;
  }
  
  public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
    return false;
  }
}
