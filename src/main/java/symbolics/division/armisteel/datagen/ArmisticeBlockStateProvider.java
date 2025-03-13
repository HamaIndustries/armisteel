package symbolics.division.armisteel.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import symbolics.division.armisteel.ArmiBlocks;

import java.util.List;

public class ArmisticeBlockStateProvider extends FabricModelProvider {
    public ArmisticeBlockStateProvider(FabricDataOutput output) {
        super(output);
    }

    public void cubeAllWithItem(List<Block> blocks, BlockStateModelGenerator generator) {
        for (Block block : blocks) generator.registerSimpleCubeAll(block);
    }

    private void bulb(Block block, BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(
                        Properties.LIT,
                        TexturedModel.CUBE_ALL.upload(block, "_lit", generator.modelCollector),
                        TexturedModel.CUBE_ALL.upload(block, generator.modelCollector)
                )));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        cubeAllWithItem(List.copyOf(ArmiBlocks.ARMISTEEL_PLATING.blocks()), generator);
        cubeAllWithItem(List.copyOf(ArmiBlocks.CORRUGATED_ARMISTEEL.blocks()), generator);
        cubeAllWithItem(List.copyOf(ArmiBlocks.RIGIDIZED_ARMISTEEL.blocks()), generator);
        cubeAllWithItem(List.copyOf(ArmiBlocks.ARMISTEEL_MESH.blocks()), generator);
        cubeAllWithItem(List.copyOf(ArmiBlocks.ARMISTEEL_BLOCK.blocks()), generator);
        cubeAllWithItem(List.copyOf(ArmiBlocks.ARMISTEEL_PIPING.blocks()), generator);
        cubeAllWithItem(List.copyOf(ArmiBlocks.ARMISTEEL_VENT.blocks()), generator);

        for (Block chain : ArmiBlocks.ARMISTEEL_CHAIN.blocks()) {
            generator.registerAxisRotated(
                    chain,
                    ModelIds.getBlockModelId(chain)
            );
        }


        for (Block block : ArmiBlocks.ARMISTEEL_BULB.blocks()) bulb(block, generator);
        for (Block block : ArmiBlocks.ARMISTEEL_BARS.blocks()) {
            TextureMap textureMap = TextureMap.paneAndTopForEdge(block, block);
            Identifier identifier = Models.TEMPLATE_GLASS_PANE_POST.upload(block, textureMap, generator.modelCollector);
            Identifier identifier2 = Models.TEMPLATE_GLASS_PANE_SIDE.upload(block, textureMap, generator.modelCollector);
            Identifier identifier3 = Models.TEMPLATE_GLASS_PANE_SIDE_ALT.upload(block, textureMap, generator.modelCollector);
            Identifier identifier4 = Models.TEMPLATE_GLASS_PANE_NOSIDE.upload(block, textureMap, generator.modelCollector);
            Identifier identifier5 = Models.TEMPLATE_GLASS_PANE_NOSIDE_ALT.upload(block, textureMap, generator.modelCollector);
            Item item = block.asItem();
//            Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(block), generator.modelCollector);
            generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block).with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)).with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3)).with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4)).with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5)).with(When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R270)));
        }
//        for (Block block : ArmiBlocks.ARMISTEEL_TRAPDOOR) generator.registerTrapdoor(block);
//        for (Block block : ArmiBlocks.ARMISTEEL_DOOR) generator.registerDoor(block);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
