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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataWriter;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.text.WordUtils;
import symbolics.division.armisteel.ArmiBlocks;
import symbolics.division.armisteel.ArmistItems;
import symbolics.division.armisteel.Armisteel;
import symbolics.division.armisteel.ArmisteelType;

import java.util.Map;
import java.util.Objects;
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
        pack.addProvider(ArmiFabricModelProvider::new);
    }

    private static class ArmiFabricModelProvider extends FabricModelProvider {
        public ArmiFabricModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public String getName() {
            return "Fabric Model Definitions";
        }

        @Override
        public CompletableFuture<?> run(DataWriter writer) {
            return super.run(writer);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(ArmistItems.MUSIC_FILE_RECALLED, Models.GENERATED);
        }
    }

    private static class ArmiModelProvider extends FusionModelProvider {
        public ArmiModelProvider(FabricDataOutput output) {
            super(Armisteel.MOD_ID, output);
        }

        @Override
        public String getName() {
            return "Fusion Model Definitions";
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
        public void generateTranslations(RegistryWrapper.WrapperLookup lookup, TranslationBuilder builder) {
            for (Block normal : ArmiBlocks.getNormalBlocks()) {
                addByID(normal, builder, lookup);
            }

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

            builder.add("item.armisteel.music_file_recalled", "Music File");
        }

        private void addAll(ArmiBlocks.BlockType type, TranslationBuilder builder) {
            for (Map.Entry<ArmisteelType, Block> pair : type.map().entrySet()) {
                builder.add(
                        pair.getValue(),
                        pair.getKey().getName() + type.name
                );
            }
        }

        private void addByID(Block block, TranslationBuilder builder, RegistryWrapper.WrapperLookup lookup) {
            Identifier id = Objects.requireNonNull(Registries.BLOCK.getId(block));
            builder.add(
                    block,
                    // sowwy echo
                    WordUtils.capitalizeFully(id.getPath().replace("_", " "))
            );
        }
    }
}