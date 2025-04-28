package house.greenhouse.examplemod.server.dedicated;

import house.greenhouse.examplemod.ExampleMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(
		value = ExampleMod.MOD_ID,
		dist = Dist.DEDICATED_SERVER
)
public class ExampleModDedicatedNeoForge {
	public ExampleModDedicatedNeoForge() {
		ExampleModDedicated.init();
	}
}
