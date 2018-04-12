package dungeon.master.components;

import dungeon.master.enumerations.Option;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class Environment extends Map implements EnvironmentService {
	
	
	@Override
	public Option<MobService> getCellContent(int x, int y) {
		return null;
	}

}
