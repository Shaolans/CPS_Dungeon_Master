package dungeon.master.decorators;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Option;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class EnvironmentServiceDecorator extends EditMapServiceDecorator implements EnvironmentService {

	public EnvironmentServiceDecorator(EnvironmentService delegate) {
		super(delegate);
	}

	public EnvironmentService getDelegate() {
		return (EnvironmentService)map;
	}

	@Override
	public Option<MobService> getCellContent(int x, int y) {
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

	@Override
	public void setCellContent(int col, int row, MobService mob) {
		getDelegate().setCellContent(col, row, mob);
	}

}
