package dungeon.master.ui.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dungeon.master.components.Cow;
import dungeon.master.components.Monster;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.contracts.MonsterServiceContract;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Dir;
import dungeon.master.enumerations.Option;
import dungeon.master.services.CowService;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;
import dungeon.master.services.MonsterService;
import dungeon.master.ui.MainWindow;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;

public class EnvironmentMouvements implements EnvironmentService {
	private static boolean game = false;
	private EnvironmentService env;
	GridPane grille;
	public List<MonsterService> ennemis = new ArrayList<>();
	private EnvironmentService serv ;

	public EnvironmentMouvements(EnvironmentService env, GridPane grille) {
		this.env = env;
		this.grille = grille;
	}

	public void setService(EnvironmentService serv) {
		this.serv = serv;
	}
	
	public static void setGame(boolean b) {
		game = b;
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

		int i=0, j=0;
		Random r = new Random();

		for(Node n : grille.getChildren()){
			StackPane sp = (StackPane) n;
			ImageView im = (ImageView)sp.getChildren().get(sp.getChildren().size()-1);

			if(i!=0 && j!=0) {

				if(im.getId()!=null) {

					switch(im.getId()){
						case "EMP":
							env.setNature(i, j, Cell.EMP);

							if(game && r.nextInt(80)==1) {
								im = new ImageView(MainWindow.cowMoves.get(0).getImage());
								im.setId("COW");
								sp.getChildren().add(im);
								System.out.println("AJOUT");
								MonsterService c = new MonsterServiceContract( new MonsterMouvements(new Monster()));
								ennemis.add(c);
								if(serv!=null)
								c.init(serv, i, j, Dir.E, 4, 2);
								env.setCellContent(i, j, c);


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
				}
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
