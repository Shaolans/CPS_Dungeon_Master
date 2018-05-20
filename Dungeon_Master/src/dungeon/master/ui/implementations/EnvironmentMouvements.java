package dungeon.master.ui.implementations;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Option;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;
import javafx.scene.layout.GridPane;

public class EnvironmentMouvements implements EnvironmentService {
	
	private EnvironmentService env;
	GridPane grille;

	public EnvironmentMouvements(EnvironmentService env, GridPane grille) {
		this.env = env;
		this.grille = grille;
	}
	
	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return env.isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return env.isReady();
	}

	@Override
	public void setNature(int col, int row, Cell cell) {
		// TODO Auto-generated method stub
		env.setNature(col, row, cell);
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return env.getHeight();
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return env.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(int w, int h) {
		// TODO Auto-generated method stub
		env.init(w, h);
	}

	@Override
	public void openDoor(int x, int y) {
		// TODO Auto-generated method stub
		env.openDoor(x, y);
	}

	@Override
	public Cell[][] getArray() {
		// TODO Auto-generated method stub
		return env.getArray();
	}

	@Override
	public Option<MobService> getCellContent(int x, int y) {
		// TODO Auto-generated method stub
		return env.getCellContent(x, y);
	}

	@Override
	public void closeDoor(int col, int row) {
		// TODO Auto-generated method stub
		env.closeDoor(col, row);
	}

	@Override
	public void setCellContent(int col, int row, MobService mob) {
		// TODO Auto-generated method stub
		env.setCellContent(col, row, mob);
	}

}
