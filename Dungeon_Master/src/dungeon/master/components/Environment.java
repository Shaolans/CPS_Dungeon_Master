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
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				mobMap[i][j] = new Option<MobService>();
			}
		}
	}
	
	@Override
	public Option<MobService> getCellContent(int x, int y) {
		return mobMap[x][y];
	}
	
	@Override
	public void closeDoor(int col, int row){
		if(getCellContent(col, row).getValue()==null){
			super.closeDoor(col, row);
		}
	}

	@Override
	public void setCellContent(int col, int row, MobService mob) {
		mobMap[col][row] = new Option<MobService>(mob);
	}

}
