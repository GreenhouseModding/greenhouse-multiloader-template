package house.greenhouse.examplemod.client.platform.side;

import house.greenhouse.examplemod.ExampleMod;
import house.greenhouse.examplemod.platform.side.ExampleSideHelper;

public class ExampleClientSideHelper implements ExampleSideHelper<ExampleClientSideHelper> {
	public static ExampleClientSideHelper getInstance() {
		return (ExampleClientSideHelper) ExampleMod.getSideHelper();
	}

	@Override
	public Class<? extends ExampleSideHelper<ExampleClientSideHelper>> type() {
		return ExampleClientSideHelper.class;
	}

	@Override
	public ExampleSideHelper<ExampleClientSideHelper> get() {
		return this;
	}
}
