package damisterboss.gary.box.custom.generation;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class GBStructures {

    public static StructureFeature<StructurePoolFeatureConfig> SLIGHTLY_DAMAGED_SHRINE = new SlightlyDamagedShrine(StructurePoolFeatureConfig.CODEC);

    public static void setupAndRegisterStructureFeatures() {

        FabricStructureBuilder.create(new Identifier("garybox", "slightly_damaged_shrine"), SLIGHTLY_DAMAGED_SHRINE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(10, 5, 399117345)).register();

        // Add more structures here and so on
    }
}
