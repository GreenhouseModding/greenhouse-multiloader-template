package house.greenhouse.examplemod.client;

import net.fabricmc.api.ClientModInitializer;

public class ExampleModClientFabric implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ExampleModClient.init();
	}
}
