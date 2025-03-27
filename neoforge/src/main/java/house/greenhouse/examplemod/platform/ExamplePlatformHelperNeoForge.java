package house.greenhouse.examplemod.platform;

import house.greenhouse.examplemod.platform.side.Side;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class ExamplePlatformHelperNeoForge implements ExamplePlatformHelper {
    @Override
    public Platform getPlatform() {
        return Platform.NEOFORGE;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

	@Override
	public Side getSide() {
		return switch (FMLLoader.getDist()) {
			case CLIENT -> Side.CLIENT;
			case DEDICATED_SERVER -> Side.DEDICATED;
		};
	}
}
