package house.greenhouse.examplemod.platform.side;

import house.greenhouse.examplemod.ExampleMod;

import java.util.ServiceLoader;

/**
 * Shared utilities for side-specific behavior.
 */
public interface ExampleSideHelper<S extends ExampleSideHelper<S>> extends ServiceLoader.Provider<ExampleSideHelper<S>> {
	static ExampleSideHelper<?> load() {
		var loaders = ServiceLoader.load(ExampleSideHelper.class);
		// Maintain sanity
		if (loaders.stream().findAny().isEmpty()) {
			throw new IllegalStateException("No " + ExampleSideHelper.class.getName() + " implementation found");
		}

		return loaders
				.stream()
				.filter(provider -> {
					final String pkg = provider.type().getPackage().getName();
					return switch (ExampleMod.getHelper().getSide()) {
						case CLIENT -> pkg.startsWith(ExampleMod.CLIENT_PACKAGE);
						case DEDICATED -> pkg.startsWith(ExampleMod.DEDICATED_PACKAGE);
					};
				})
				.findFirst()
				.orElseThrow()
				.get();
	}
}
