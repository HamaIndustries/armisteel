package symbolics.division.armisteel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Armisteel implements ModInitializer {
    public static final String MOD_ID = "armisteel";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ArmiBlocks.init();
        ArmistItems.init();

        Registry.register(
                Registries.ITEM_GROUP,
                id("armistice"),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemgroup.armisteel"))
                        .icon(() -> ArmiBlocks.ARMISTEEL_BLOCK.get(ArmisteelType.DEFAULT).asItem().getDefaultStack())
                        .entries((displayContext, entries) -> {
                            for (Block block : ArmiBlocks.ARMISTEEL_GRATE.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_PLATING.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.CORRUGATED_ARMISTEEL.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_PIPING.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_VENT.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_MESH.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.RIGIDIZED_ARMISTEEL.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_BLOCK.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_BULB.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_CHAIN.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_BARS.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_TRAPDOOR.blocks()) entries.add(block);
                            for (Block block : ArmiBlocks.ARMISTEEL_DOOR.blocks()) entries.add(block);

                            entries.add(ArmiBlocks.CRYOSTONE);
                            entries.add(ArmiBlocks.CRYOBBLESTONE);

                            entries.add(ArmiBlocks.CORRODESTONE);
                            entries.add(ArmiBlocks.CORRODLESTONE);
                            entries.add(ArmiBlocks.OOZING_CORRODLESTONE);

                            entries.add(ArmiBlocks.SCORCHSTONE);
                            entries.add(ArmiBlocks.SCORBBLESTONE);

                            entries.add(ArmiBlocks.SCORCHED_DIRT);
                            entries.add(ArmiBlocks.SCORCHED_GRASS);

                            entries.add(ArmiBlocks.ASH);
                            entries.add(ArmiBlocks.SLAG);

                            entries.add(ArmistItems.MUSIC_FILE_RECALLED);
                        })
                        .build()
        );
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}