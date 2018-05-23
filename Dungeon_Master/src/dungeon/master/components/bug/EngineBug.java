package dungeon.master.components.bug;

import java.util.ArrayList;
import java.util.List;

import dungeon.master.enumerations.Cell;
import dungeon.master.services.EngineService;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.PlayerService;

public class EngineBug implements EngineService {
	private List<EntityService> entities;
	private EnvironmentService envi;
	private PlayerService player;
	
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
	public void init(EnvironmentService env, PlayerService player) {
		entities = new ArrayList<>();
		envi = env;
		this.player = player;

	}

	@Override
	public void removeEntity(int i) {
		envi.setCellContent(entities.get(i).getCol(), entities.get(i).getRow(), null);
		entities.remove(i);
	}

	@Override
	public void addEntity(EntityService entity) {
		entities.add(entity);
		envi.setCellContent(entity.getCol(), entity.getRow(), entity);
	}

	public void clean() {
		List<EntityService> todelete = new ArrayList<>();
		for(EntityService ent: entities) {
			if(ent.getHp() <= 0) {
				todelete.add(ent);
			}
		}
		for(EntityService ent: todelete) {
			getEnvi().setCellContent(ent.getCol(), ent.getRow(), null);
		}
		
		entities.removeIf(e->e.getHp() <= 0);
	}
	@Override
	public void step() {
		for(EntityService ent: entities) {
			ent.step();
		}
		
	}

	@Override
	public PlayerService getPlayer() {
		return player;
	}

	@Override
	public boolean isFinished() {
		return isOut() && getPlayer().foundTreasure();
	}

	@Override
	public boolean isOut() {
		int xi = 0;
		int yi = 0;
		for(int i = 0; i < getEnvi().getWidth(); i++) {
			for(int j = 0; j < getEnvi().getHeight(); j++) {
				if(getEnvi().getCellNature(i, j) == Cell.OUT) {
					xi = i;
					yi = j;
				}
			}
		}
		
		if(getPlayer().getCol() == xi && getPlayer().getRow() == yi) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLost() {
		return getPlayer().getHp() <= 0;
	}


}
