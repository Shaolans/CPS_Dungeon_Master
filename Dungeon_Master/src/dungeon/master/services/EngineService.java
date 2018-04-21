package dungeon.master.services;

import java.util.List;

public interface EngineService {
	/* Observators */
	public EnvironmentService getEnvi();
	
	public List<EntityService> getEntities();
	
	public EntityService getEntity(int i);
	
	/* Invariants */
	// \inv \forall i in [0;getEntities().size()-1], getEntity(i).getEnvi() == getEnvi()
	// \inv \forall i in [0;getEntities().size()-1], getEnvi().getCellContent(getEntity(i).getCol(), getEntity(i).getRow()) == getEntity(i)
	
	
	/* Constructors */
	public void init(EnvironmentService env);
	
	/* Operators */
	
	// \pre 0 <= i < getEntities.size()
	public void removeEntity(int i);
	// \post getEntities.size() == getEntities.size()@pre -1
	// \post \forall k in [0,i-1] getEntities(k) == getEntities(k)@pre
	// \post \forall k in [i,getEntities.size()-2], getEntities(k) == getEntities(k+1)@pre
	
	
	public void addEntity(EntityService entity);
	// \post getEntities.size() == getEntities.size()@pre +1
	// \post \forall k in [0,i-1] getEntities(k) == getEntities(k)@pre
	// \post getEntity(getEntities().size()-1) = entity
	
	// \pre \forall i in [0;getEntities().size()-1], getEntity(i).getHp() > 0
	public void step();

}
