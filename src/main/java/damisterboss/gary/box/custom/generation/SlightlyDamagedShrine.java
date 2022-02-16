package damisterboss.gary.box.custom.generation;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.PostPlacementProcessor;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.apache.logging.log4j.Level;

import damisterboss.gary.box.GaryBox;

import java.util.Optional;

public class SlightlyDamagedShrine extends StructureFeature<StructurePoolFeatureConfig> {

    public SlightlyDamagedShrine(Codec<StructurePoolFeatureConfig> codec) {
        super(codec, SlightlyDamagedShrine::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }

    public static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_MONSTERS = Pool.of();

    public static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_CREATURES = Pool.of(
            new SpawnSettings.SpawnEntry(GaryBox.GARY, 30, 3, 10));

    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        BlockPos spawnXZPosition = context.chunkPos().getCenterAtY(0);

        int landHeight = context.chunkGenerator().getHeightInGround(spawnXZPosition.getX(), spawnXZPosition.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG, context.world());

        VerticalBlockSample columnOfBlocks = context.chunkGenerator().getColumnSample(spawnXZPosition.getX(),
                spawnXZPosition.getZ(), context.world());

        BlockState topBlock = columnOfBlocks.getState(landHeight);

        return topBlock.getFluidState().isEmpty(); // alternate: landHeight > 100;
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(
            StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        if (!SlightlyDamagedShrine.isFeatureChunk(context)) {
            return Optional.empty();
        }

        StructurePoolFeatureConfig newConfig = new StructurePoolFeatureConfig(

                () -> context.registryManager().get(Registry.STRUCTURE_POOL_KEY)
                        .get(new Identifier("garybox", "slightly_damaged_shrine/start_pool")),

                10);

        // Create a new context with the new config that has our json pool. We will pass
        // this into JigsawPlacement.addPieces
        StructureGeneratorFactory.Context<StructurePoolFeatureConfig> newContext = new StructureGeneratorFactory.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.world(),
                context.validBiome(),
                context.structureManager(),
                context.registryManager());

        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator = StructurePoolBasedGenerator
                .generate(newContext, PoolStructurePiece::new, blockpos.down(4), false, true);

        if (structurePiecesGenerator.isPresent()) {
            // I use to debug and quickly find out if the structure is spawning or not and
            // where it is.
            // This is returning the coordinates of the center starting piece.
            GaryBox.LOGGER.log(Level.DEBUG, "Structure at " + blockpos);
        }

        return structurePiecesGenerator;
    }
}