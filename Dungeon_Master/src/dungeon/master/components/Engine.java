package dungeon.master.components;

import java.util.ArrayList;
import java.util.List;

import dungeon.master.services.EngineService;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;

public class Engine implements EngineService {
	private List<EntityService> entities;
	private EnvironmentService envi;
	
	@Override
	public EnvironmentService getEnvi() {
		return envi;
	}

	@Override
	public List<EntityService> getEntities() {
		return entities;
	}

	@Override
	public EntityService getEntity(int i) {
		return entities.get(i);
	}

	@Override
	public void init(EnvironmentService env) {
		entities = new ArrayList<>();
		envi = env;

	}

	@Override
	public void removeEntity(int i) {
		entities.remove(i);
	}

	@Override
	public void addEntity(EntityService entity) {
		entities.add(entity);
	}

	@Override
	public void step() {
		for(EntityService ent: entities) {
			ent.step();
		}
	}

}
