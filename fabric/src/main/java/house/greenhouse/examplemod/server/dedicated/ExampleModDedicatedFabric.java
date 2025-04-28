package house.greenhouse.examplemod.server.dedicated;

import net.fabricmc.api.DedicatedServerModInitializer;

public class ExampleModDedicatedFabric implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		ExampleModDedicated.init();
	}
}
