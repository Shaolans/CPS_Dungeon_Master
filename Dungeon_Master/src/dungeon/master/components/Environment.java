package dungeon.master.components;

import java.util.Vector;

import dungeon.master.enumerations.Option;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class Environment extends Map implements EnvironmentService {
	
	Vector<Vector<Option<MobService>>>  mobMap;
	
	public Environment(){
		mobMap = new Vector<Vector<Option<MobService>>>(getHeight());
		
		for(int i=0; i<getHeight(); i++){
			mobMap.add(new Vector<Option<MobService>>(getWidth()));
			
		}
	}
	
	@Override
	public Option<MobService> getCellContent(int x, int y) {
		return mobMap.get(x).get(y);
	}
	
	@Override
	public void closeDoor(int col, int row){
		if(getCellContent(col, row).getValue()==null){
			super.closeDoor(col, row);
		}
	}

}
