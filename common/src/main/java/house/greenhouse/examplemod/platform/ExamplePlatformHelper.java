package house.greenhouse.examplemod.platform;

import house.greenhouse.examplemod.ExampleMod;
import house.greenhouse.examplemod.platform.side.Side;
import org.jetbrains.annotations.ApiStatus;

import java.util.ServiceLoader;

public interface ExamplePlatformHelper extends ServiceLoader.Provider<ExamplePlatformHelper> {
	static ExamplePlatformHelper getInstance() {
		return ExampleMod.getHelper();
	}

	/**
	 * Gets the current platform
	 *
	 * @return An enum value representing the current platform.
	 */
	Platform getPlatform();

	/**
	 * Gets a user-friendly string of the current platform's name.
	 *
	 * @return A string representing the current platform's name.
	 */
	default String getPlatformName() {
		return switch (getPlatform()) {
			case FABRIC -> "Fabric";
			case NEOFORGE -> "NeoForge";
		};
	}

	/**
	 * Checks if a mod with the given id is loaded.
	 *
	 * @param modId The mod to check if it is loaded.
	 * @return True if the mod is loaded, false otherwise.
	 */
	boolean isModLoaded(String modId);

	/**
	 * Check if the game is currently in a development environment.
	 *
	 * @return True if in a development environment, false otherwise.
	 */
	boolean isDevelopmentEnvironment();

	/**
	 * Gets the current distribution side.
	 *
	 * @return The distribution side that this mod is running in.
	 */
	Side getSide();

	@ApiStatus.Internal
	static ExamplePlatformHelper load() {
		var loaders = ServiceLoader.load(ExamplePlatformHelper.class);
		// Maintain sanity
		if (loaders.stream().findAny().isEmpty()) {
			throw new IllegalStateException("No " + ExamplePlatformHelper.class.getName() + " implementation found");
		}

		return loaders
				.stream()
				.findFirst()
				.orElseThrow()
				.get();
	}
}
