package house.greenhouse.examplemod.client.platform.side;

import house.greenhouse.examplemod.ExampleMod;
import house.greenhouse.examplemod.platform.side.ExampleSideHelper;
import org.jetbrains.annotations.ApiStatus;

public class ExampleClientSideHelper implements ExampleSideHelper<ExampleClientSideHelper> {
	public static ExampleClientSideHelper getInstance() {
		return (ExampleClientSideHelper) ExampleMod.getSideHelper();
	}

	@Override
	@ApiStatus.Internal
	public Class<? extends ExampleSideHelper<ExampleClientSideHelper>> type() {
		return ExampleClientSideHelper.class;
	}

	@Override
	@ApiStatus.Internal
	public ExampleSideHelper<ExampleClientSideHelper> get() {
		return this;
	}
}
