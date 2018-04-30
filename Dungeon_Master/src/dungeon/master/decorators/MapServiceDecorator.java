package dungeon.master.decorators;

import dungeon.master.enumerations.Cell;
import dungeon.master.services.MapService;

public class MapServiceDecorator implements MapService{

	protected MapService map;
		
	public MapServiceDecorator(MapService map) {
		this.map = map;
	}

	public MapService getDelegate(){
		return map;
	}
	
	public int getHeight() {
		return map.getHeight();
	}

	public int getWidth() {
		return map.getWidth();
	}

	public Cell getCellNature(int x, int y) {
		return map.getCellNature(x, y);
	}

	public void init(int w, int h) {
		map.init(w, h);
	}

	public void openDoor(int x, int y) {
		map.openDoor(x, y);
	}

	public void closeDoor(int x, int y) {
		map.closeDoor(x, y);
	}

	@Override
	public Cell[][] getArray() {
		return map.getArray();
	}

	

}
