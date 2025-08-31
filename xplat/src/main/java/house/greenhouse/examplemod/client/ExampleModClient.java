package house.greenhouse.examplemod.client;

import house.greenhouse.examplemod.ExampleMod;
import org.slf4j.Logger;

public class ExampleModClient {
	public static final Logger LOG = ExampleMod.getLogger("Client");

	public static void init() {
		LOG.info("Initializing {} Client", ExampleMod.MOD_NAME);
	}
}
