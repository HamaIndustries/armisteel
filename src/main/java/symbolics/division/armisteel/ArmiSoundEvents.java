package symbolics.division.armisteel;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

public class ArmiSoundEvents {
    public static final SoundEvent BLOCK$ARMISTEEL$PLACE = of("block.armisteel.place");

    public static final SoundEvent BLOCK$ARMISTEEL$BREAK = of("block.armisteel.break");

    public static final SoundEvent BLOCK$ARMISTEEL$FALL = of("block.armisteel.fall");

    public static final SoundEvent BLOCK$ARMISTEEL$HIT = of("block.armisteel.hit");

    public static final SoundEvent BLOCK$ARMISTEEL$STEP = of("block.armisteel.step");

    public static class Types {
        public static final BlockSoundGroup ARMISTEEL = new BlockSoundGroup(
                1.0F,
                1.0F,
                BLOCK$ARMISTEEL$BREAK,
                BLOCK$ARMISTEEL$STEP,
                BLOCK$ARMISTEEL$PLACE,
                BLOCK$ARMISTEEL$HIT,
                BLOCK$ARMISTEEL$FALL
        );
    }

    public static SoundEvent of(String path) {
        return Registry.register(
                Registries.SOUND_EVENT,
                Armisteel.id(path),
                SoundEvent.of(Armisteel.id(path))
        );
    }
}
