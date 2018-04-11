package dungeon.master.decorators;

import dungeon.master.enumerations.Cell;
import dungeon.master.services.EnvironmentService;

public class EnvironmentServiceDecorator extends MapServiceDecorator implements EnvironmentService {
	
	public EnvironmentServiceDecorator(EnvironmentService delegate) {
		super(delegate);
	}

	public EnvironmentService getDelegate() {
		return (EnvironmentService)map;
	}
	
	@Override
	public boolean getCellContent(int x, int y) {
		return getDelegate().getCellContent(x, y);
	}

	@Override
	public int getHeight() {
		return getDelegate().getHeight();
	}

	@Override
	public int getWidth() {
		return getDelegate().getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return getDelegate().getCellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		getDelegate().init(w, h);
	}

	@Override
	public void openDoor(int x, int y) {
		getDelegate().openDoor(x, y);
	}

	@Override
	public void closeDoor(int col, int row) {
		getDelegate().closeDoor(col, row);
		
	}

}
