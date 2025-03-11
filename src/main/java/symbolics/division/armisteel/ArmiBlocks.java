package symbolics.division.armisteel;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.BulbBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.GrateBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArmiBlocks {
    public static final List<Block> ARMISTEEL_GRATE = makeVariants(
            () -> new GrateBlock(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(BlockSoundGroup.COPPER_GRATE)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
                            .allowsSpawning(Blocks::never)
                            .solidBlock((state, level, pos) -> false)
                            .blockVision((state, level, pos) -> false)
                            .suffocates((state, level, pos) -> false)
                            .nonOpaque()
            ),
            "armisteel_grate"
    );

    public static final List<Block> ARMISTEEL_PLATING = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_plating"
    );

    public static final List<Block> CORRUGATED_ARMISTEEL = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "corrugated_armisteel"
    );

    public static final List<Block> ARMISTEEL_PIPING = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_piping"
    );

    public static final List<Block> ARMISTEEL_VENT = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_vent"
    );

    public static final List<Block> ARMISTEEL_MESH = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_mesh"
    );

    public static final List<Block> RIGIDIZED_ARMISTEEL = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "rigidized_armisteel"
    );

    public static final List<Block> ARMISTEEL_BLOCK = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_block"
    );

    public static final List<Block> ARMISTEEL_BULB = makeVariants(
            () -> new BulbBlock(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
                            .solidBlock((state, level, pos) -> false)
                            .luminance(state -> state.get(Properties.LIT) ? 15 : 0)
            ),
            "armisteel_bulb"
    );

    public static final List<Block> ARMISTEEL_CHAIN = makeVariants(
            () -> new ArmisteelChainBlock(
                    AbstractBlock.Settings.create()
                            .solid()
                            .requiresTool()
                            .strength(5.0F, 6.0F)
                            .sounds(BlockSoundGroup.CHAIN)
                            .nonOpaque()
            ),
            "armisteel_chain"
    );

    public static final List<Block> ARMISTEEL_BARS = makeVariants(
            () -> new PaneBlock(
                    AbstractBlock.Settings.create().
                            requiresTool()
                            .strength(5.0F, 6.0F)
                            .sounds(BlockSoundGroup.CHAIN)
                            .nonOpaque()
            ),
            "armisteel_bars"
    );

    public static final List<Block> ARMISTEEL_TRAPDOOR = makeVariants(
            () -> new TrapdoorBlock(
                    BlockSetType.IRON,
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_trapdoor"
    );

    public static final List<Block> ARMISTEEL_DOOR = makeVariants(
            () -> new DoorBlock(
                    BlockSetType.IRON,
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_door"
    );

    private static List<Block> makeVariants(Supplier<Block> supplier, String id) {
        List<Block> list = new ArrayList<>();
        for (ArmisteelType variant : ArmisteelType.values()) {
            Block block = supplier.get();
            Registry.register(
                    Registries.BLOCK,
                    Armisteel.id(variant.id() + id),
                    block
            );
            list.add(block);
        }
        return list;
    }

    /**
     * force class loading
     */
    public static void init() {
        // NO-OP
    }
}