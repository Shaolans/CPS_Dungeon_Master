package dungeon.master.decorators;

import dungeon.master.enumerations.Cell;
import dungeon.master.services.EditMapService;

public class EditMapServiceDecorator extends MapServiceDecorator implements
		EditMapService {

	public EditMapServiceDecorator(EditMapService map) {
		super(map);
	}
	
	public EditMapService getDelegate(){
		return ((EditMapService)super.getDelegate());
	}
	
	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return getDelegate().isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		return getDelegate().isReady();
	}

	@Override
	public void setNature(int col, int row, Cell cell) {
		getDelegate().setNature(col, row, cell);
	}

}
