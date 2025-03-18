package symbolics.division.armisteel.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import symbolics.division.armisteel.ArmiBlocks;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArmisticeBlockStateProvider extends FabricModelProvider {
    public ArmisticeBlockStateProvider(FabricDataOutput output) {
        super(output);
    }

    public static TextureKey BARS = TextureKey.of("bars");

    public static Model IRON_BARS_CAP = block("iron_bars_cap", "_cap", BARS, TextureKey.EDGE);

    public static Model IRON_BARS_CAP_ALT = block("iron_bars_cap_alt", "_cap_alt", BARS, TextureKey.EDGE);

    public static Model IRON_BARS_POST = block("iron_bars_post", "_post", BARS);

    public static Model IRON_BARS_POST_ENDS = block("iron_bars_post_ends", "_post_ends", TextureKey.EDGE);

    public static Model IRON_BARS_SIDE = block("iron_bars_side", "_side", BARS, TextureKey.EDGE);

    public static Model IRON_BARS_SIDE_ALT = block("iron_bars_side_alt", "_side_alt", BARS, TextureKey.EDGE);


    private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.ofVanilla("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    public void cubeAllWithItem(List<Block> blocks, BlockStateModelGenerator generator) {
        for (Block block : blocks) generator.registerSimpleCubeAll(block);
    }

    private void bulb(Block block, BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(
                        Properties.LIT,
                        generator.createSubModel(block, "_lit", Models.CUBE_ALL, TextureMap::all),
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
        cubeAllWithItem(List.copyOf(ArmiBlocks.ARMISTEEL_GRATE.blocks()), generator);

        generator.registerSimpleCubeAll(ArmiBlocks.CRYOSTONE);
        generator.registerSimpleCubeAll(ArmiBlocks.CRYOBBLESTONE);

        generator.registerSimpleCubeAll(ArmiBlocks.CORRODESTONE);
        generator.registerSimpleCubeAll(ArmiBlocks.CORRODLESTONE);
        generator.registerSimpleCubeAll(ArmiBlocks.OOZING_CORRODLESTONE);

        generator.registerSimpleCubeAll(ArmiBlocks.SCORCHSTONE);
        generator.registerSimpleCubeAll(ArmiBlocks.SCORBBLESTONE);

        generator.registerSimpleCubeAll(ArmiBlocks.SCORCHED_DIRT);

        generator.registerSimpleCubeAll(ArmiBlocks.ASH);
        generator.registerSimpleCubeAll(ArmiBlocks.SLAG);

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(
                ArmiBlocks.SCORCHED_GRASS,
                BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(TexturedModel.CUBE_BOTTOM_TOP
                        .get(ArmiBlocks.SCORCHED_GRASS)
                        .textures(textures -> textures.put(TextureKey.BOTTOM, TextureMap.getId(ArmiBlocks.SCORCHED_DIRT)))
                        .upload(ArmiBlocks.SCORCHED_GRASS, generator.modelCollector)))
        );


        for (Block chain : ArmiBlocks.ARMISTEEL_CHAIN.blocks()) {
            generator.registerAxisRotated(
                    chain,
                    Models.CROSS.upload(chain, TextureMap.cross(chain), generator.modelCollector)
            );

            generator.registerItemModel(chain);
        }


        for (Block block : ArmiBlocks.ARMISTEEL_BULB.blocks()) bulb(block, generator);
        for (Block block : ArmiBlocks.ARMISTEEL_BARS.blocks()) {
            TextureMap barsEdge = new TextureMap()
                    .put(BARS, TextureMap.getId(block))
                    .put(TextureKey.EDGE, TextureMap.getId(block));

            TextureMap bars = new TextureMap().put(BARS, TextureMap.getId(block));

            TextureMap edge = new TextureMap().put(TextureKey.EDGE, TextureMap.getId(block));

            Identifier cap = IRON_BARS_CAP.upload(block, barsEdge, generator.modelCollector);
            Identifier capAlt = IRON_BARS_CAP_ALT.upload(block, barsEdge, generator.modelCollector);
            Identifier post = IRON_BARS_POST.upload(block, bars, generator.modelCollector);
            Identifier postEnds = IRON_BARS_POST_ENDS.upload(block, edge, generator.modelCollector);
            Identifier side = IRON_BARS_SIDE.upload(block, barsEdge, generator.modelCollector);
            Identifier sideAlt = IRON_BARS_SIDE_ALT.upload(block, barsEdge, generator.modelCollector);

            When nt = When.create().set(Properties.NORTH, true);
            When nf = When.create().set(Properties.NORTH, false);

            When st = When.create().set(Properties.SOUTH, true);
            When sf = When.create().set(Properties.SOUTH, false);

            When et = When.create().set(Properties.EAST, true);
            When ef = When.create().set(Properties.EAST, false);

            When wt = When.create().set(Properties.WEST, true);
            When wf = When.create().set(Properties.WEST, false);

            Models.GENERATED.upload(ModelIds.getItemModelId(block.asItem()), TextureMap.layer0(block), generator.modelCollector);

            generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                    .with(BlockStateVariant.create().put(VariantSettings.MODEL, postEnds))
                    .with(When.allOf(nf, sf, ef, wf), BlockStateVariant.create().put(VariantSettings.MODEL, post))
                    .with(When.allOf(nt, sf, ef, wf), BlockStateVariant.create().put(VariantSettings.MODEL, cap))
                    .with(
                            When.allOf(nf, sf, et, wf),
                            BlockStateVariant.create().put(VariantSettings.MODEL, cap)
                                    .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                    )
                    .with(When.allOf(nf, st, ef, wf), BlockStateVariant.create().put(VariantSettings.MODEL, capAlt))
                    .with(
                            When.allOf(nf, sf, ef, wt),
                            BlockStateVariant.create().put(VariantSettings.MODEL, capAlt)
                                    .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                    )
                    .with(nt, BlockStateVariant.create().put(VariantSettings.MODEL, side))
                    .with(
                            et,
                            BlockStateVariant.create().put(VariantSettings.MODEL, side)
                                    .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                    )
                    .with(st, BlockStateVariant.create().put(VariantSettings.MODEL, sideAlt))
                    .with(
                            wt,
                            BlockStateVariant.create().put(VariantSettings.MODEL, sideAlt)
                                    .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                    ));
        }

        for (Block block : ArmiBlocks.ARMISTEEL_TRAPDOOR.blocks()) generator.registerTrapdoor(block);
        for (Block block : ArmiBlocks.ARMISTEEL_DOOR.blocks()) generator.registerDoor(block);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
