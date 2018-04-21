package dungeon.master.contracts;

import java.util.ArrayList;
import java.util.List;

import dungeon.master.decorators.EngineServiceDecorator;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EngineService;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;

public class EngineServiceContract extends EngineServiceDecorator implements EngineService {

	public EngineServiceContract(EngineService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		for(int i = 0; i < getEntities().size(); i++) {
			if(!(getEntity(i).getEnv()==getEnvi())) {
				throw new InvariantError("\\inv \\forall i in [0;getEntities().size()-1], getEntity(i).getEnvi() == getEnvi() does not hold");
			}
			
			int x = getEntity(i).getCol();
			int y = getEntity(i).getRow();
			if(!(getEnvi().getCellContent(x, y) == getEntity(i))) {
				throw new InvariantError("\\inv \\forall i in [0;getEntities().size()-1], getEnvi().getCellContent(getEntity(i).getCol(), getEntity(i).getRow()) == getEntity(i)");
			}
		}
	}
	
	@Override
	public void init(EnvironmentService env) {
		
		super.init(env);
		
		checkInvariant();
	}
	
	
	@Override
	public void removeEntity(int i) {
		/* Preconditions */
		if(!(i >= 0 && i < getEntities().size())) {
			throw new PreconditionError("0 <= i < getEntities.size() does not hold");
		}
		
		/* Invariants */
		checkInvariant();
		
		/* Capture */
		List<EntityService> entities_atpre = new ArrayList<>(getEntities());
		
		/* Run */
		super.removeEntity(i);
		
		/* Invariants */
		checkInvariant();
		
		/* Postconditions */
		if(!(getEntities().size() == entities_atpre.size()-1)) {
			throw new PostconditionError("getEntities.size() == getEntities.size()@pre -1 does not hold");
		}
		
		for(int j = 0; j < i; j++) {
			if(!(entities_atpre.get(j) == getEntity(j))) {
				throw new PostconditionError("\\forall k in [0,i-1] getEntities(k) == getEntities(k)@pre does not hold");
			}
		}
		
		for(int j = i; j < getEntities().size(); j++) {
			if(!(entities_atpre.get(j+1) == getEntity(j))) {
				throw new PostconditionError("\forall k in [i,getEntities.size()-2], getEntities(k) == getEntities(k+1)@pre does not hold");
			}
		}
	}
	
	@Override
	public void addEntity(EntityService entity) {
		/* Preconditions */
		//NONE
		
		/* Invariants */
		checkInvariant();
		
		/* Capture */
		List<EntityService> entities_atpre = new ArrayList<>(getEntities());
		
		/* Run */
		super.addEntity(entity);
		
		/* Invariants */
		checkInvariant();
		
		/* Postconditions */
		if(!(getEntities().size() == entities_atpre.size()+1)) {
			throw new PostconditionError("getEntities.size() == getEntities.size()@pre +1 does not hold");
		}
		
		for(int j = 0; j < getEntities().size()-1; j++) {
			if(!(entities_atpre.get(j) == getEntity(j))) {
				throw new PostconditionError("\\forall k in [0,i-1] getEntities(k) == getEntities(k)@pre does not hold");
			}
		}
		
		if(!(getEntity(getEntities().size()-1) == entity)) {
			throw new PostconditionError("getEntity(getEntities().size()-1) = entity does not hold");
		}
	}
	
	@Override
	public void step() {
		/* Preconditions */
		for(int i = 0; i < getEntities().size(); i++) {
			if(!(getEntity(i).getHp()>0)) {
				throw new PreconditionError("\\forall i in [0;getEntities().size()-1], getEntity(i).getHp() > 0 does not hold");
			}
		}
		
		/* Invariants */
		checkInvariant();
		
		/* Run */
		super.step();
		
		/*Invariants*/
		checkInvariant();
		
		/* Postconditions */
		//NONE
	}

}
