package dungeon.master.contracts;

import dungeon.master.decorators.EngineServiceDecorator;
import dungeon.master.services.EngineService;

public class EngineServiceContract extends EngineServiceDecorator implements EngineService {

	public EngineServiceContract(EngineService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		
	}

}
