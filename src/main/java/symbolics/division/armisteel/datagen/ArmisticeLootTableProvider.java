package symbolics.division.armisteel.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import symbolics.division.armisteel.ArmiBlocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ArmisticeLootTableProvider extends FabricBlockLootTableProvider {
	public ArmisticeLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		addDrops(ArmiBlocks.ARMISTEEL_GRATE);
		addDrops(ArmiBlocks.ARMISTEEL_PLATING);
		addDrops(ArmiBlocks.CORRUGATED_ARMISTEEL);
		addDrops(ArmiBlocks.ARMISTEEL_CHAIN);
		addDrops(ArmiBlocks.ARMISTEEL_MESH);
		addDrops(ArmiBlocks.ARMISTEEL_BLOCK);
		addDrops(ArmiBlocks.ARMISTEEL_PIPING);
		addDrops(ArmiBlocks.ARMISTEEL_VENT);
		addDrops(ArmiBlocks.RIGIDIZED_ARMISTEEL);
		addDrops(ArmiBlocks.ARMISTEEL_BULB);
		addDrops(ArmiBlocks.ARMISTEEL_BARS);
		addDrops(ArmiBlocks.ARMISTEEL_DOOR);
		addDrops(ArmiBlocks.ARMISTEEL_TRAPDOOR);
	}

	private void addDrops(List<Block> blocks) {
		for (Block block : blocks) addDrop(block);
	}

}
