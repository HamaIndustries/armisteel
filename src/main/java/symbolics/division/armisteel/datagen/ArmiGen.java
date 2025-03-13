package symbolics.division.armisteel.datagen;

import com.supermartijn642.fusion.api.model.DefaultModelTypes;
import com.supermartijn642.fusion.api.model.ModelInstance;
import com.supermartijn642.fusion.api.model.data.ConnectingModelDataBuilder;
import com.supermartijn642.fusion.api.predicate.DefaultConnectionPredicates;
import com.supermartijn642.fusion.api.provider.FusionModelProvider;
import com.supermartijn642.fusion.api.provider.FusionTextureMetadataProvider;
import com.supermartijn642.fusion.api.texture.DefaultTextureTypes;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureData;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureLayout;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import symbolics.division.armisteel.ArmiBlocks;
import symbolics.division.armisteel.Armisteel;

public class ArmiGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ArmisticeBlockStateProvider::new);
        pack.addProvider(ArmisticeLootTableProvider::new);
        pack.addProvider(ArmiModelProvider::new);
        pack.addProvider(ArmiTextureMetadataProvider::new);
    }

    private static class ArmiModelProvider extends FusionModelProvider {
        public ArmiModelProvider(FabricDataOutput output) {
            super(Armisteel.MOD_ID, output);
        }

        @Override
        protected void generate() {
            blockAllConnecting(ArmiBlocks.ARMISTEEL_GRATE);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_MESH);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_PIPING);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_PLATING);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_VENT);
            blockAllConnecting(ArmiBlocks.CORRUGATED_ARMISTEEL);
            blockAllConnecting(ArmiBlocks.RIGIDIZED_ARMISTEEL);
        }

        private void blockAllConnecting(ArmiBlocks.BlockType type) {
            for (Block block : type.blocks()) {
                Identifier blockID = Registries.BLOCK.getId(block);
                Identifier path = blockID.withPrefixedPath("block/");
                var modelData = ConnectingModelDataBuilder.builder()
                        .parent(Identifier.ofVanilla("block/cube_all"))
                        .texture("all", path)
                        .connection(DefaultConnectionPredicates.isSameBlock())
                        .build();
                var modelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, modelData);
                this.addModel(path, modelInstance);
            }
        }
    }


    private class ArmiTextureMetadataProvider extends FusionTextureMetadataProvider {

        public ArmiTextureMetadataProvider(FabricDataOutput output) {
            super(Armisteel.MOD_ID, output);
        }

        @Override
        protected void generate() {
            blockAllConnecting(ArmiBlocks.ARMISTEEL_GRATE);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_MESH);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_PIPING);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_PLATING);
            blockAllConnecting(ArmiBlocks.ARMISTEEL_VENT);
            blockAllConnecting(ArmiBlocks.CORRUGATED_ARMISTEEL);
            blockAllConnecting(ArmiBlocks.RIGIDIZED_ARMISTEEL);
        }

        private void blockAllConnecting(ArmiBlocks.BlockType type) {
            for (Block block : type.blocks()) {
                Identifier blockID = Registries.BLOCK.getId(block);
                Identifier path = blockID.withPrefixedPath("block/");
                var textureData = ConnectingTextureData.builder()
                        .layout(ConnectingTextureLayout.FULL)
                        .build();
                this.addTextureMetadata(
                        path,
                        DefaultTextureTypes.CONNECTING,
                        textureData
                );
            }
        }
    }
}