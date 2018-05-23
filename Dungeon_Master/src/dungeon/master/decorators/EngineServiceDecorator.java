package dungeon.master.decorators;

import java.util.List;

import dungeon.master.services.EngineService;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.PlayerService;

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

	public void init(EnvironmentService env, PlayerService player) {
		delegate.init(env, player);
	}

	public void removeEntity(int i) {
		delegate.removeEntity(i);
	}

	public void addEntity(EntityService entity) {
		delegate.addEntity(entity);
	}

	public void step() {
		delegate.step();
	}

	@Override
	public PlayerService getPlayer() {
		return this.delegate.getPlayer();
	}

	@Override
	public boolean isFinished() {
		return this.delegate.isFinished();	
	}

	@Override
	public void clean() {
		delegate.clean();
		
	}

	@Override
	public boolean isOut() {
		return delegate.isOut();
	}

	@Override
	public boolean isLost() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
