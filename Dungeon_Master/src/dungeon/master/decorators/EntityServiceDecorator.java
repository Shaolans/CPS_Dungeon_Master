package dungeon.master.decorators;

import dungeon.master.enumerations.Dir;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class EntityServiceDecorator extends MobServiceDecorator implements
		EntityService {
	
	public EntityServiceDecorator(MobService decorator) {
		super(decorator);
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
		getDelegate().init(env, col, row, dir, hp);
	}
	
	


}
