package dungeon.master.components;

import dungeon.master.enumerations.Cell;
import dungeon.master.services.MapService;

public class Map implements MapService {
	private Cell[][] cellmap;
	
	public Map(){
		
	}
	
	@Override
	public int getHeight() {
		return cellmap.length;
	}

	@Override
	public int getWidth() {
		return cellmap[0].length;
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return cellmap[x][y];
	}

	@Override
	public void init(int w, int h) {
		cellmap = new Cell[h][w];
	}

	@Override
	public void openDoor(int x, int y) {
		if(cellmap[x][y] == Cell.DWC){
			cellmap[x][y] = Cell.DWO;
		} else if (cellmap[x][y] == Cell.DNC){
			cellmap[x][y] = Cell.DNO;
		}
	}

	@Override
	public void closeDoor(int x, int y) {
		if(cellmap[x][y] == Cell.DWO){
			cellmap[x][y] = Cell.DWC;
		} else if (cellmap[x][y] == Cell.DNO){
			cellmap[x][y] = Cell.DNC;
		}

	}

}