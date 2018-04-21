package dungeon.master.decorators;

import java.util.List;

import dungeon.master.services.EngineService;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;

public class EngineServiceDecorator implements EngineService {
	private EngineService delegate;
	
	public EngineServiceDecorator(EngineService delegate) {
		this.delegate = delegate;
	}

	public EnvironmentService getEnvi() {
		return delegate.getEnvi();
	}

	public List<EntityService> getEntities() {
		return delegate.getEntities();
	}

	public EntityService getEntity(int i) {
		return delegate.getEntity(i);
	}

	public void init(EnvironmentService env) {
		delegate.init(env);
	}

	public void removeEntity(int i) {
		delegate.removeEntity(i);
	}

	public void addEntry(EntityService entity) {
		delegate.addEntry(entity);
	}

	public void step() {
		delegate.step();
	}
	
	
}
