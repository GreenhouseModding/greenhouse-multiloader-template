package house.greenhouse.examplemod;

import house.greenhouse.examplemod.platform.ExamplePlatformHelperFabric;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		ExampleMod.setHelper(new ExamplePlatformHelperFabric());
		ExampleMod.LOG.info("Hello Fabric world!");
		ExampleMod.init();
	}
}
