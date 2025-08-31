package house.greenhouse.examplemod;

import house.greenhouse.examplemod.platform.ExamplePlatformHelper;
import house.greenhouse.examplemod.platform.side.ExampleSideHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod {
	public static final String MOD_ID = "examplemod";
	public static final String MOD_NAME = "Example Mod";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
	// These strings are for the PlatformHelper API.
	// Don't forget to update the service config in
	// "common/src/resources/META-INF/services" after updating the package!
	public static final String CLIENT_PACKAGE = "house.greenhouse.examplemod.client";
	public static final String DEDICATED_PACKAGE = "house.greenhouse.examplemod.server.dedicated";

	private static ExamplePlatformHelper helper;
	private static ExampleSideHelper<?> sideHelper;

	public static void init() {
		LOG.info("Initializing {} Common on platform {}", ExampleMod.MOD_NAME, getHelper().getPlatformName());
	}

	public static Logger getLogger(String... subsystems) {
		return LoggerFactory.getLogger(MOD_NAME + "/" + String.join("/", subsystems));
	}

	public static ExamplePlatformHelper getHelper() {
		if (helper == null) {
			helper = ExamplePlatformHelper.load();
		}
		return helper;
	}

	public static ExampleSideHelper<?> getSideHelper() {
		if (sideHelper == null) {
			sideHelper = ExampleSideHelper.load();
		}
		return sideHelper;
	}
}
