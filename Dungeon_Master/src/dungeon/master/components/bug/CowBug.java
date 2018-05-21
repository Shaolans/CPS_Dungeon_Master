package dungeon.master.components.bug;

import dungeon.master.enumerations.Dir;
import dungeon.master.services.CowService;

public class CowBug extends EntityBug implements CowService {

	@Override
	public void step() {
		int step = (int)(Math.random()*6);
		
		switch(step) {
		case 0:
			if(face == Dir.N && row+1 < env.getHeight() ||
			face == Dir.S && row-1 >= 0 ||
			face == Dir.E && col-1 >= 0 ||
			face == Dir.W && col+1 < env.getWidth()) {
				forward();
				forward();
			}
			break;
		case 1:
			if(face == Dir.S && row+1 < env.getHeight() ||
			face == Dir.N && row-1 >= 0 ||
			face == Dir.W && col-1 >= 0 ||
			face == Dir.E && col+1 < env.getWidth()) {
				backward();
			}
			break;
		case 2:
			if(face == Dir.E && row+1 < env.getHeight() ||
			face == Dir.W && row-1 >= 0 ||
			face == Dir.S && col-1 >= 0 ||
			face == Dir.N && col+1 < env.getWidth()) {
				strafeL();
			}
			
			break;
		case 3:
			if(face == Dir.W && row+1 < env.getHeight() ||
			face == Dir.E && row-1 >= 0 ||
			face == Dir.N && col-1 >= 0 ||
			face == Dir.S && col+1 < env.getWidth()) {
				strafeR();
			}
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
