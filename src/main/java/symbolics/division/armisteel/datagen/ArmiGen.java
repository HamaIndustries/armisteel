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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import symbolics.division.armisteel.ArmiBlocks;
import symbolics.division.armisteel.Armisteel;
import symbolics.division.armisteel.ArmisteelType;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ArmiGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ArmisticeBlockStateProvider::new);
        pack.addProvider(ArmisticeLootTableProvider::new);
        pack.addProvider(ArmiModelProvider::new);
        pack.addProvider(ArmiTextureMetadataProvider::new);
        pack.addProvider(ArmiLanguageProvider::new);
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


    private static class ArmiTextureMetadataProvider extends FusionTextureMetadataProvider {
        
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

    private static class ArmiLanguageProvider extends FabricLanguageProvider {
        protected ArmiLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
            addAll(ArmiBlocks.ARMISTEEL_GRATE, builder);
            addAll(ArmiBlocks.ARMISTEEL_PLATING, builder);
            addAll(ArmiBlocks.CORRUGATED_ARMISTEEL, builder);
            addAll(ArmiBlocks.ARMISTEEL_PIPING, builder);
            addAll(ArmiBlocks.ARMISTEEL_VENT, builder);
            addAll(ArmiBlocks.ARMISTEEL_MESH, builder);
            addAll(ArmiBlocks.RIGIDIZED_ARMISTEEL, builder);
            addAll(ArmiBlocks.ARMISTEEL_BLOCK, builder);
            addAll(ArmiBlocks.ARMISTEEL_BULB, builder);
            addAll(ArmiBlocks.ARMISTEEL_CHAIN, builder);
            addAll(ArmiBlocks.ARMISTEEL_BARS, builder);
            addAll(ArmiBlocks.ARMISTEEL_TRAPDOOR, builder);
            addAll(ArmiBlocks.ARMISTEEL_DOOR, builder);

            builder.add(
                    RegistryKey.of(
                            RegistryKeys.ITEM_GROUP,
                            Armisteel.id("armistice")
                    ),
                    "Armisteel"
            );
        }
        
        private void addAll(ArmiBlocks.BlockType type, TranslationBuilder builder) {
            for (Map.Entry<ArmisteelType, Block> pair : type.map().entrySet()) {
                builder.add(
                        pair.getValue(),
                        pair.getKey().getName() + type.name
                );
            }
        }
    }
}