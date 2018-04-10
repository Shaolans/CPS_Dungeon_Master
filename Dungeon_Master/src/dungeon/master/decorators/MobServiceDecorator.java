package dungeon.master.decorators;

import dungeon.master.enumerations.Dir;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class MobServiceDecorator implements MobService {
	private MobService decorator;
	
	public EnvironmentService getEnv() {
		return decorator.getEnv();
	}

	public int getCol() {
		return decorator.getCol();
	}

	public int getRow() {
		return decorator.getRow();
	}

	public Dir getFace() {
		return decorator.getFace();
	}

	public void init(EnvironmentService env, int row, int col, Dir dir) {
		decorator.init(env, row, col, dir);
	}

	public void forward() {
		decorator.forward();
	}

	public void backward() {
		decorator.backward();
	}

	public void turnL() {
		decorator.turnL();
	}

	public void turnR() {
		decorator.turnR();
	}

	public void StrafeL() {
		decorator.StrafeL();
	}

	public void StrafeR() {
		decorator.StrafeR();
	}

	public MobServiceDecorator(MobService decorator) {
		this.decorator = decorator;
	}
	
}