package symbolics.division.armisteel;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class ArmistItems {
    public static final Item MUSIC_FILE_RECALLED = register("music_file_recalled",
            new Item(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
                    .jukeboxPlayable(ArmiSoundEvents.KEY$MUSIC$RECALLED))
    );

    private static <T extends Item> T register(String id, T item) {
        return Registry.register(Registries.ITEM, Armisteel.id(id), item);
    }

    public static void init() {
        // NO-OP
    }
}
