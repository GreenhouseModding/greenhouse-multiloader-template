package house.greenhouse.examplemod.server.dedicated;

import house.greenhouse.examplemod.ExampleMod;
import org.slf4j.Logger;

public class ExampleModDedicated {
	public static final Logger LOG = ExampleMod.getLogger("Dedicated Server");

	public static void init() {
		LOG.info("Initializing {} Dedicated Server", ExampleMod.MOD_NAME);
	}
}
