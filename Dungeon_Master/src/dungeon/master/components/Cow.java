package dungeon.master.components;

import dungeon.master.services.CowService;

public class Cow extends Entity implements CowService {

	@Override
	public void step() {
		int step = (int)(Math.random()*6);
		switch(step) {
		case 0:
			forward();
			break;
		case 1:
			backward();
			break;
		case 2:
			StrafeL();
			break;
		case 3:
			StrafeR();
			break;
		case 4:
			turnL();
			break;
		case 5:
			turnR();
			break;
		}
	}

}
