package symbolics.division.armisteel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class ArmisteelClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for (Block bars : ArmiBlocks.ARMISTEEL_BARS.blocks())
            BlockRenderLayerMap.INSTANCE.putBlock(bars, RenderLayer.getCutout());

		for (Block chain : ArmiBlocks.ARMISTEEL_CHAIN.blocks())
			BlockRenderLayerMap.INSTANCE.putBlock(chain, RenderLayer.getCutout());

		for (Block grate : ArmiBlocks.ARMISTEEL_GRATE.blocks())
			BlockRenderLayerMap.INSTANCE.putBlock(grate, RenderLayer.getCutout());
	}
}