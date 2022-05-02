package damisterboss.gary.box;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item.Settings;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.GeckoLib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import damisterboss.gary.box.custom.block.Crown;
import damisterboss.gary.box.custom.block.EmptyGaryBox;
import damisterboss.gary.box.custom.block.GaryCake;
import damisterboss.gary.box.custom.block.GaryTypeBox;
import damisterboss.gary.box.custom.block.HardHat;
import damisterboss.gary.box.custom.block.OpenGaryBox;
import damisterboss.gary.box.custom.block.TopHat;
import damisterboss.gary.box.custom.entity.BusinessGary;
import damisterboss.gary.box.custom.entity.ConstructionGary;
import damisterboss.gary.box.custom.entity.Gary;
import damisterboss.gary.box.custom.entity.HoverGary;
import damisterboss.gary.box.custom.entity.KingGary;
import damisterboss.gary.box.custom.entity.LargeGary;
import damisterboss.gary.box.custom.entity.MedicGary;
import damisterboss.gary.box.custom.item.GaryFoodComponents;
import damisterboss.gary.box.custom.item.GaryMatterItem;
import damisterboss.gary.box.custom.item.GarySauceItem;

public class GaryBox implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("garybox");

	// Creates a creative tab for GaryBox
	public static final ItemGroup GARYBOX = FabricItemGroupBuilder.create(new Identifier("garybox", "general")).icon(() -> new ItemStack(GaryBox.GARY_COOKIE))
	.appendItems(stacks -> {
		stacks.add(new ItemStack(GaryBox.ESSENCE_OF_GARY));
		stacks.add(new ItemStack(GaryBox.GARY_MATTER));
		stacks.add(new ItemStack(GaryBox.GARY_GOO));

		stacks.add(new ItemStack(GaryBox.GARY_SAUCE));
		stacks.add(new ItemStack(GaryBox.GARY_COOKIE));
		stacks.add(new ItemStack(GaryBox.GARY_CAKE));

		stacks.add(new ItemStack(GaryBox.EMPTY_GARY_BOX));
		stacks.add(new ItemStack(GaryBox.GARY_TYPE_BOX));
		stacks.add(new ItemStack(GaryBox.GARY_BLOCK));
		
		stacks.add(new ItemStack(GaryBox.TOPHAT));
		stacks.add(new ItemStack(GaryBox.HARD_HAT));
		stacks.add(new ItemStack(GaryBox.CROWN));
		stacks.add(new ItemStack(GaryBox.CYBERNETICS));

		stacks.add(new ItemStack(GaryBox.GARY_FRAME));
		stacks.add(new ItemStack(GaryBox.GARY_ORE));

	})
	.build();

	// Ores & Materials
	public static final Item ESSENCE_OF_GARY = new Item(new Item.Settings().group(GaryBox.GARYBOX));
	public static final Block GARY_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3f, 3).sounds(BlockSoundGroup.STONE).requiresTool());
	public static final Block GARY_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5f, 6).sounds(BlockSoundGroup.METAL).requiresTool());
	public static final Item GARY_GOO = new Item(new Item.Settings().group(GaryBox.GARYBOX));
	public static final Item BUCKET_OF_GOO = new Item(new Item.Settings().group(GaryBox.GARYBOX));
	public static final Item GARY_MATTER = new GaryMatterItem(new Item.Settings().group(GaryBox.GARYBOX).food(GaryFoodComponents.GARY_MATTER));
	public static final Item GARY_FRAME = new Item(new Item.Settings().group(GaryBox.GARYBOX));
	public static final Item CYBERNETICS = new Item(new Item.Settings().group(GaryBox.GARYBOX));

	// Boxes
	public static final Block EMPTY_GARY_BOX = new EmptyGaryBox(FabricBlockSettings.of(Material.WOOD).strength(1f, 2).sounds(BlockSoundGroup.WOOD));
	public static final Block OPEN_GARY_BOX = new OpenGaryBox(FabricBlockSettings.of(Material.WOOD).strength(1f, 2).sounds(BlockSoundGroup.WOOD)); //Need to fix textures on bottom of box to match empty gary box
	public static final Block GARY_TYPE_BOX = new GaryTypeBox(FabricBlockSettings.of(Material.WOOD).strength(1f, 2).sounds(BlockSoundGroup.WOOD));

	// Food
	public static final Item GARY_SAUCE = new GarySauceItem(new Item.Settings().group(GaryBox.GARYBOX).food(GaryFoodComponents.GARY_SAUCE));
	public static final Item GARY_COOKIE = new Item(new Item.Settings().group(GaryBox.GARYBOX).food(GaryFoodComponents.GARY_COOKIE));
	public static final Block GARY_CAKE = new GaryCake(FabricBlockSettings.of(Material.WOOD).strength(0.5f, 0.5f).sounds(BlockSoundGroup.WOOL));

	// Hats
	public static final Block TOPHAT = new TopHat(FabricBlockSettings.of(Material.WOOL).strength(1f, 0).sounds(BlockSoundGroup.WOOL));
	public static final Block HARD_HAT = new HardHat(FabricBlockSettings.of(Material.METAL).strength(1f, 0).sounds(BlockSoundGroup.BONE));
	public static final Block CROWN = new Crown(FabricBlockSettings.of(Material.METAL).strength(1f, 0).sounds(BlockSoundGroup.AMETHYST_BLOCK));
	
	// Gary^3
    public static final Item GARY_SPAWN_EGG = new SpawnEggItem(GaryBox.GARY, 16777215, 16448250, new Item.Settings());

	public static final EntityType<Gary> GARY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("garybox", "gary"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Gary::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);
	
	public static final EntityType<LargeGary> LARGE_GARY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("garybox", "large_gary"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LargeGary::new).dimensions(EntityDimensions.fixed(0.99f, 0.99f)).build()
	);
	
	public static final EntityType<BusinessGary> BUSINESS_GARY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("garybox", "business_gary"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BusinessGary::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);
	
	public static final EntityType<ConstructionGary> CONSTRUCTION_GARY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("garybox", "construction_gary"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ConstructionGary::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);
	
	public static final EntityType<KingGary> KING_GARY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("garybox", "king_gary"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, KingGary::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);
	
	public static final EntityType<HoverGary> HOVER_GARY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("garybox", "hover_gary"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HoverGary::new).dimensions(EntityDimensions.changing(0.75f, 1.6f)).build()
	);
	
	public static final EntityType<MedicGary> MEDIC_GARY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("garybox", "medic_gary"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MedicGary::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	// Sounds and what they mean
	public static final Identifier GARY_AMBIENT = new Identifier("garybox:gary_ambient");
	public static final Identifier GARY_HURT = new Identifier("garybox:gary_hurt");
	public static final Identifier GARY_DEATH = new Identifier("garybox:gary_death");
	public static SoundEvent GARY_AMBIENT_EVENT = new SoundEvent(GARY_AMBIENT);
	public static SoundEvent GARY_HURT_EVENT = new SoundEvent(GARY_HURT);
	public static SoundEvent GARY_DEATH_EVENT = new SoundEvent(GARY_DEATH);

	@Override
	public void onInitialize() {
		
		LOGGER.info("Gary Initialized");

		// GeckoLib for Animation
		GeckoLib.initialize();

		// Registers all of the bips and bons
		Registry.register(Registry.ITEM, new Identifier("garybox", "essence_of_gary"), ESSENCE_OF_GARY);
		Registry.register(Registry.BLOCK, new Identifier("garybox", "gary_ore"), GARY_ORE);
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_ore"), new BlockItem(GARY_ORE, new Settings().group(GaryBox.GARYBOX)));
		Registry.register(Registry.BLOCK, new Identifier("garybox", "gary_block"), GARY_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_block"), new BlockItem(GARY_BLOCK, new Settings().group(GaryBox.GARYBOX)));
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_goo"), GARY_GOO);
		Registry.register(Registry.ITEM, new Identifier("garybox", "bucket_of_goo"), BUCKET_OF_GOO);
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_matter"), GARY_MATTER);
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_frame"), GARY_FRAME);
		Registry.register(Registry.ITEM, new Identifier("garybox", "cybernetics"), CYBERNETICS);
		
		Registry.register(Registry.BLOCK, new Identifier("garybox", "empty_gary_box"), EMPTY_GARY_BOX);
		Registry.register(Registry.ITEM, new Identifier("garybox", "empty_gary_box"), new BlockItem(EMPTY_GARY_BOX, new Settings().group(GaryBox.GARYBOX)));
		Registry.register(Registry.BLOCK, new Identifier("garybox", "open_gary_box"), OPEN_GARY_BOX);
		Registry.register(Registry.ITEM, new Identifier("garybox", "open_gary_box"), new BlockItem(OPEN_GARY_BOX, new Settings().group(GaryBox.GARYBOX)));
		Registry.register(Registry.BLOCK, new Identifier("garybox", "gary_type_box"), GARY_TYPE_BOX);
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_type_box"), new BlockItem(GARY_TYPE_BOX, new Settings().group(GaryBox.GARYBOX)));
		
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_cookie"), GARY_COOKIE);
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_sauce"), GARY_SAUCE);
		Registry.register(Registry.BLOCK, new Identifier("garybox", "gary_cake"), GARY_CAKE);
		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_cake"), new BlockItem(GARY_CAKE, new Settings().group(GaryBox.GARYBOX)));
		
		Registry.register(Registry.BLOCK, new Identifier("garybox", "tophat"), TOPHAT);
		Registry.register(Registry.ITEM, new Identifier("garybox", "tophat"), new BlockItem(TOPHAT, new Settings().group(GaryBox.GARYBOX)));
		Registry.register(Registry.BLOCK, new Identifier("garybox", "hard_hat"), HARD_HAT);
		Registry.register(Registry.ITEM, new Identifier("garybox", "hard_hat"), new BlockItem(HARD_HAT, new Settings().group(GaryBox.GARYBOX)));
		Registry.register(Registry.BLOCK, new Identifier("garybox", "crown"), CROWN);
		Registry.register(Registry.ITEM, new Identifier("garybox", "crown"), new BlockItem(CROWN, new Settings().group(GaryBox.GARYBOX)));

		Registry.register(Registry.ITEM, new Identifier("garybox", "gary_spawn_egg"), GARY_SPAWN_EGG);

		FabricDefaultAttributeRegistry.register(GARY, Gary.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D));
		FabricDefaultAttributeRegistry.register(LARGE_GARY, LargeGary.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D));
		FabricDefaultAttributeRegistry.register(BUSINESS_GARY, BusinessGary.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D));
		FabricDefaultAttributeRegistry.register(CONSTRUCTION_GARY, ConstructionGary.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D));
		FabricDefaultAttributeRegistry.register(KING_GARY, KingGary.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D));
		FabricDefaultAttributeRegistry.register(HOVER_GARY, HoverGary.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.2D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F));
		FabricDefaultAttributeRegistry.register(MEDIC_GARY, MedicGary.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D));

		Registry.register(Registry.SOUND_EVENT, GaryBox.GARY_AMBIENT, GARY_AMBIENT_EVENT);
		Registry.register(Registry.SOUND_EVENT, GaryBox.GARY_HURT, GARY_HURT_EVENT);
		Registry.register(Registry.SOUND_EVENT, GaryBox.GARY_DEATH, GARY_DEATH_EVENT);
	}
}

// This code will never be directly used, but is meant to archive the means by which to make a block that mimics the properties of bamboo and other plants (moving around on placed) for 1.18

//public static final Block GARY_SAUCE = new GarySauce(FabricBlockSettings.of(Material.METAL).strength(0f, 0).sounds(BlockSoundGroup.METAL).breakByHand(true).dynamicBounds());
	// .dynamicBounds is required for any block that functions like bamboo and moves around
//Registry.register(Registry.ITEM, new Identifier("garybox", "gary_sauce"), new GarySauceItem(new Item.Settings().group(GaryBox.GARY_BOX).food(GaryFoodComponents.GARY_SAUCE)));