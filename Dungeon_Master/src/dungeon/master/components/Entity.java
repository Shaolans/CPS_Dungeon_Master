package dungeon.master.components;

import dungeon.master.enumerations.Dir;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;

public abstract class Entity extends Mob implements EntityService {
	private int hp;

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp) {
		super.init(env, col, row, dir);
		this.hp = hp;

	}
	
	@Override
	public int getHp() {
		return hp;
	}

}
