package dungeon.master.components;


import dungeon.master.enumerations.Option;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class Environment extends EditMap implements EnvironmentService {

	Option<EntityService> mobMap[][];

	@SuppressWarnings("unchecked")
	@Override
	public void init(int w, int h) {
		super.init(w, h);
		mobMap = (Option<EntityService>[][]) new Option[w][h];
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				mobMap[i][j] = new Option<EntityService>();
			}
		}
	}

	@Override
	public Option<EntityService> getCellContent(int x, int y) {
		return mobMap[x][y];
	}

	@Override
	public void closeDoor(int col, int row){
		if(getCellContent(col, row).getValue()==null){
			super.closeDoor(col, row);
		}
	}

	@Override
	public void setCellContent(int col, int row, EntityService mob) {
		mobMap[col][row] = new Option<EntityService>(mob);
	}

}
