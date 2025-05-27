package house.greenhouse.examplemod.platform;

import house.greenhouse.examplemod.platform.side.Side;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.ApiStatus;

public class ExamplePlatformHelperFabric implements ExamplePlatformHelper {
	@Override
	public Platform getPlatform() {
		return Platform.FABRIC;
	}

	@Override
	public boolean isModLoaded(String modId) {
		return FabricLoader.getInstance().isModLoaded(modId);
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return FabricLoader.getInstance().isDevelopmentEnvironment();
	}

	@Override
	public Side getSide() {
		return switch (FabricLoader.getInstance().getEnvironmentType()) {
			case CLIENT -> Side.CLIENT;
			case SERVER -> Side.DEDICATED;
		};
	}

	@Override
	@ApiStatus.Internal
	public Class<? extends ExamplePlatformHelper> type() {
		return ExamplePlatformHelperFabric.class;
	}

	@Override
	@ApiStatus.Internal
	public ExamplePlatformHelper get() {
		return this;
	}
}
