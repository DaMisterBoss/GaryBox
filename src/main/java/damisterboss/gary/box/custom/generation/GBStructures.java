package damisterboss.gary.box.custom.generation;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class GBStructures {

    public static StructureFeature<StructurePoolFeatureConfig> SLIGHTLY_DAMAGED_SHRINE = new SlightlyDamagedShrine(StructurePoolFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> MODERATELY_DAMAGED_SHRINE = new ModeratelyDamagedShrine(StructurePoolFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> HEAVILY_DAMAGED_SHRINE = new HeavilyDamagedShrine(StructurePoolFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> PRISTINE_SHRINE = new PristineShrine(StructurePoolFeatureConfig.CODEC);

    public static void setupAndRegisterStructureFeatures() {

        FabricStructureBuilder.create(new Identifier("garybox", "slightly_damaged_shrine"), SLIGHTLY_DAMAGED_SHRINE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(10, 5, 224281233)).register();

        FabricStructureBuilder.create(new Identifier("garybox", "moderately_damaged_shrine"), MODERATELY_DAMAGED_SHRINE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(10, 5, 685316163)).register();

        FabricStructureBuilder.create(new Identifier("garybox", "heavily_damaged_shrine"), HEAVILY_DAMAGED_SHRINE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(10, 5, 778547421)).register();

        FabricStructureBuilder.create(new Identifier("garybox", "pristine_shrine"), PRISTINE_SHRINE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(10, 5, 157276357)).register();

        // Add more structures here and so on
    }
}
