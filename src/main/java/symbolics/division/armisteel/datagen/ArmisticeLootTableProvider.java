package symbolics.division.armisteel.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import symbolics.division.armisteel.ArmiBlocks;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ArmisticeLootTableProvider extends FabricBlockLootTableProvider {
	public ArmisticeLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		addDrops(ArmiBlocks.ARMISTEEL_GRATE.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_PLATING.blocks());
		addDrops(ArmiBlocks.CORRUGATED_ARMISTEEL.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_CHAIN.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_MESH.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_BLOCK.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_PIPING.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_VENT.blocks());
		addDrops(ArmiBlocks.RIGIDIZED_ARMISTEEL.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_BULB.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_BARS.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_DOOR.blocks());
		addDrops(ArmiBlocks.ARMISTEEL_TRAPDOOR.blocks());
	}

	private void addDrops(Collection<Block> blocks) {
		for (Block block : blocks) addDrop(block);
	}

}
