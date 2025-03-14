package symbolics.division.armisteel;

import com.sun.source.tree.IdentifierTree;
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
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArmiBlocks {
    public static final class BlockType {
        private Map<ArmisteelType, Block> blocks = new HashMap<>();
        public final String type;
        public final String name;
        public BlockType(String type, String name) {
            this.type = type;
            this.name = name;
        }

        private void add(ArmisteelType armisteelType, Block block) {
            blocks.put(armisteelType, block);
        }

        public Block get(ArmisteelType armisteelType) {
            return blocks.get(armisteelType);
        }

        public Collection<Block> blocks() { return blocks.values(); }
        public Map<ArmisteelType, Block> map() { return blocks; }
    }

    public static final BlockType ARMISTEEL_GRATE = makeVariants(
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
            "armisteel_grate", "Armisteel Grate"
    );

    public static final BlockType ARMISTEEL_PLATING = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_plating", "Armisteel Plating"
    );

    public static final BlockType CORRUGATED_ARMISTEEL = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "corrugated_armisteel", "Corrugated Armisteel"
    );

    public static final BlockType ARMISTEEL_PIPING = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_piping", "Armisteel Piping"
    );

    public static final BlockType ARMISTEEL_VENT = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_vent", "Armisteel Vent"
    );

    public static final BlockType ARMISTEEL_MESH = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_mesh", "Armisteel Mesh"
    );

    public static final BlockType RIGIDIZED_ARMISTEEL = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "rigidized_armisteel", "Rigidized Armisteel"
    );

    public static final BlockType ARMISTEEL_BLOCK = makeVariants(
            () -> new Block(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_block", "Armisteel Block"
    );

    public static final BlockType ARMISTEEL_BULB = makeVariants(
            () -> new BulbBlock(
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
                            .solidBlock((state, level, pos) -> false)
                            .luminance(state -> state.get(Properties.LIT) ? 15 : 0)
            ),
            "armisteel_bulb", "Armisteel Bulb"
    );

    public static final BlockType ARMISTEEL_CHAIN = makeVariants(
            () -> new ArmisteelChainBlock(
                    AbstractBlock.Settings.create()
                            .solid()
                            .requiresTool()
                            .strength(5.0F, 6.0F)
                            .sounds(BlockSoundGroup.CHAIN)
                            .nonOpaque()
            ),
            "armisteel_chain", "Armisteel Chain"
    );

    public static final BlockType ARMISTEEL_BARS = makeVariants(
            () -> new PaneBlock(
                    AbstractBlock.Settings.create().
                            requiresTool()
                            .strength(5.0F, 6.0F)
                            .sounds(BlockSoundGroup.CHAIN)
                            .nonOpaque()
            ),
            "armisteel_bars", "Armisteel Bars"
    );

    public static final BlockType ARMISTEEL_TRAPDOOR = makeVariants(
            () -> new TrapdoorBlock(
                    BlockSetType.IRON,
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_trapdoor", "Armisteel Trapdoor"
    );

    public static final BlockType ARMISTEEL_DOOR = makeVariants(
            () -> new DoorBlock(
                    BlockSetType.IRON,
                    AbstractBlock.Settings.create()
                            .strength(3.0F, 6.0F)
                            .sounds(ArmiSoundEvents.Types.ARMISTEEL)
                            .mapColor(MapColor.IRON_GRAY)
                            .requiresTool()
            ),
            "armisteel_door", "Armisteel Door"
    );

    private static BlockType makeVariants(Supplier<Block> supplier, String id, String name) {
        BlockType type = new BlockType(id, name);
        for (ArmisteelType variant : ArmisteelType.values()) {
            Block block = supplier.get();
            Registry.register(
                    Registries.BLOCK,
                    Armisteel.id(variant.prefix() + id),
                    block
            );

            Registry.register(
                    Registries.ITEM,
                    Armisteel.id(variant.prefix() + id),
                    new BlockItem(block, new Item.Settings())
            );
            type.add(variant, block);
        }
        return type;
    }

    /**
     * force class loading
     */
    public static void init() {
        // NO-OP
    }
}