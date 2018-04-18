package dungeon.master.contracts;

import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;

public class EntityServiceContract extends MobServiceContract implements
		EntityService {

	public EntityServiceContract(EntityService decorator) {
		super(decorator);
	}
	
	public void checkInvariant(){
		super.checkInvariant();
	}
	
	public EntityService getDelegate(){
		return (EntityService)super.getDelegate();
	}
	
	
	@Override
	public int getHp() {
		return getDelegate().getHp();
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp) {
		/* preconditions */
		if(!(hp > 0)){
			throw new PreconditionError("h > 0 does not hold");
		}
		
		if(!(0 <= row && env.getHeight() < row)) throw new PreconditionError("0 <= row <= env.getHeight() does not hold");
		if(!(0 <= col && env.getWidth() < col)) throw new PreconditionError("0 <= col <= env.getWidth() does not hold");
		
		getDelegate().init(env, col, row, dir, hp);
		
		
		/* Invariants */
		checkInvariant();
		
		/* Postconditions */
		if(!(getEnv()==env)) throw new PostconditionError("getEnv() = env does not hold");
		if(!(getCol()==col)) throw new PostconditionError("getCol() = col does not hold");
		if(!(getRow()==row)) throw new PostconditionError("getRow() = row does not hold");
		if(!(getFace()==dir)) throw new PostconditionError("getFace() = dir does not hold");
		if(!(getHp()==hp)) throw new PostconditionError("getHp() = hp does not hold");

	}

}
