package damisterboss.gary.box.custom.generation;

import net.minecraft.structure.PlainsVillageData;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class GBConfiguredStructures {

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_SLIGHTLY_DAMAGED_SHRINE = GBStructures.SLIGHTLY_DAMAGED_SHRINE
            .configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0));
            // Dummy StructurePoolFeatureConfig values for now. We will modify the pool at runtime since we cannot get json pool files here at mod init.
            // You can create and register your pools in code, pass in the code create pool here, and delete both newConfig and newContext in RunDownHouseStructure's createPiecesGenerator.
            // Note: StructurePoolFeatureConfig only takes 0 - 7 size so that's another reason why we are going to bypass that "codec" by changing size at runtime to get higher sizes.

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new Identifier("garybox", "configured_slightly_damaged_shrine"), CONFIGURED_SLIGHTLY_DAMAGED_SHRINE);
    }
}