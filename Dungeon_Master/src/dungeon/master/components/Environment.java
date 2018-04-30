package dungeon.master.components;


import dungeon.master.enumerations.Option;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class Environment extends Map implements EnvironmentService {
	
	Option<MobService> mobMap[][];
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(int w, int h) {
		super.init(w, h);
		mobMap = (Option<MobService>[][]) new Option[w][h];
	}
	
	@Override
	public Option<MobService> getCellContent(int x, int y) {
		return mobMap[x][y];
	}
	
	@Override
	public void closeDoor(int col, int row){
		if(getCellContent(col, row)==null){
			super.closeDoor(col, row);
		}
	}

}
