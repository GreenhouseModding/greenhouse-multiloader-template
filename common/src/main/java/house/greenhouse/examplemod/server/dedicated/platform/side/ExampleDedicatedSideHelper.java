package house.greenhouse.examplemod.server.dedicated.platform.side;

import house.greenhouse.examplemod.ExampleMod;
import house.greenhouse.examplemod.platform.side.ExampleSideHelper;

public class ExampleDedicatedSideHelper implements ExampleSideHelper<ExampleDedicatedSideHelper> {
	static ExampleDedicatedSideHelper getInstance() {
		return (ExampleDedicatedSideHelper) ExampleMod.getSideHelper();
	}

	@Override
	public Class<? extends ExampleSideHelper<ExampleDedicatedSideHelper>> type() {
		return ExampleDedicatedSideHelper.class;
	}

	@Override
	public ExampleSideHelper<ExampleDedicatedSideHelper> get() {
		return this;
	}
}
