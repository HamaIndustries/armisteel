package symbolics.division.armisteel;

import com.supermartijn642.fusion.api.provider.FusionModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

public class ArmiGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

    }

    private static class ArmiModelProvider extends FusionModelProvider {
        public ArmiModelProvider(String modid, FabricDataOutput output) {
            super(modid, output);
        }

        @Override
        protected void generate() {
            // type connencting, layout fulll
        }
    }
}