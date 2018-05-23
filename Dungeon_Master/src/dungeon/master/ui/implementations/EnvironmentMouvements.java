package dungeon.master.ui.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dungeon.master.components.Cow;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Option;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;

public class EnvironmentMouvements implements EnvironmentService {

	private EnvironmentService env;
	GridPane grille;
	List<Thread> ennemis = new ArrayList<>();

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
		return env.getCellNature(x, y);
	}

	@Override
	public void init(int w, int h) {

		env.init(w, h);
		int nbEnnemi = 4;
		int i=0, j=0;
		Random r = new Random();

		for(Node n : grille.getChildren()){
			StackPane sp = (StackPane) n;
			ImageView im = (ImageView)sp.getChildren().get(sp.getChildren().size()-1);



			switch(im.getId()){
				case "EMP":
					env.setNature(i, j, Cell.EMP);

					if(r.nextInt(20)==0) {
						Cow c = new Cow();
						env.setCellContent(i, j, c);
						Thread t = new Thread(new Runnable() {

							@Override
							public void run() {
								c.step();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						t.start();
						ennemis.add(t);
					}
					break;
				case "DCN":
					env.setNature(i, j, Cell.DNC);
					break;
				case "DCW":
					env.setNature(i, j, Cell.DWC);
					break;
				case "OUT":
					env.setNature(i, j, Cell.OUT);
					break;
				case "WLL":
					env.setNature(i, j, Cell.WLL);
					break;
				case "TRS":
					env.setNature(i, j, Cell.TRS);
					break;
				case "IN":
					env.setNature(i, j, Cell.IN);
					break;

			}

			i=(i+1)%getWidth();
			if(i==0) j++;
		}

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
	public Option<EntityService> getCellContent(int x, int y) {
		// TODO Auto-generated method stub
		return env.getCellContent(x, y);
	}

	@Override
	public void closeDoor(int col, int row) {
		env.closeDoor(col, row);

	}

	@Override
	public void setCellContent(int col, int row, EntityService mob) {
		// TODO Auto-generated method stub
		env.setCellContent(col, row, mob);
	}



}
