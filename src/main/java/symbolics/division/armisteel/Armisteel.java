package symbolics.division.armisteel;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.PaneBlock;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Armisteel implements ModInitializer {
	public static final String MOD_ID = "armisteel";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ArmiBlocks.init();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}