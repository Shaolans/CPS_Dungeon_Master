package dungeon.master.ui.implementations;

import java.util.List;

import dungeon.master.decorators.EnvironmentServiceDecorator;
import dungeon.master.enumerations.Dir;
import dungeon.master.services.CowService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.ui.MainWindow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CowMouvements implements CowService {

	CowService cow;
	private ImageView face, faceD, faceG, droite, droiteD, droiteG,
	derriere, derriereD, derriereG, gauche, gaucheD, gaucheG;

	private int oldCol, oldRow;
	private Dir oldDir;
	private EnvironmentMouvements em;
	private ImageView current;

	public CowMouvements(CowService cow) {

		this.cow = cow;
		List<ImageView> moves = MainWindow.cowMoves;
		face = moves.get(0);
		faceD = moves.get(1);
		faceG = moves.get(2);
		droite = moves.get(3);
		droiteD = moves.get(4);
		droiteG = moves.get(5);
		derriere = moves.get(6);
		derriereD = moves.get(7);
		derriereG = moves.get(8);
		gauche = moves.get(9);
		gaucheD = moves.get(10);
		gaucheG = moves.get(11);
	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return cow.getHp();
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return cow.getDamage();
	}

	@Override
	public void attack() {

		cow.attack();
		StackPane sp;
		ImageView currentBis=null;

		if(current==face) {
			currentBis = faceD;
		}
		if(current==gauche) {
			currentBis = gaucheD;
		}
		if(current==droite) {
			currentBis = droiteD;
		}
		if(current==derriere) {
			currentBis = derriereD;
		}

		if(current==faceD) {
			currentBis = faceG;
		}

		if(current==faceG) {
			currentBis = faceD;
		}

		if(current==gaucheD) {
			currentBis = gaucheG;
		}

		if(current==droiteD) {
			currentBis = droiteG;
		}

		if(current==derriereD) {
			currentBis = derriereG;
		}
		if(current==derriereG) {
			currentBis = derriereD;
		}

		if(current==gaucheG) {
			currentBis = gaucheD;
		}

		if(current==droiteG) {
			currentBis = droiteD;
		}


		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().remove(current);
		sp.getChildren().add(currentBis);
		current=currentBis;

	}
	@Override
	public void setHp(int hp) {
		cow.setHp(hp);

	}

	@Override
	public EnvironmentService getEnv() {
		// TODO Auto-generated method stub
		return cow.getEnv();
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return cow.getCol();
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return cow.getRow();
	}

	@Override
	public Dir getFace() {
		// TODO Auto-generated method stub
		return cow.getFace();
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir) {
		EnvironmentServiceDecorator dec = (EnvironmentServiceDecorator) env;
		em = (EnvironmentMouvements) dec.getDelegate();
		StackPane sp = (StackPane) em.grille.getChildren().get(0);
		sp.getChildren().add(face);
		current = face;
		cow.init(env, col, row, dir);

	}

	@Override
	public void forward() {

		StackPane sp;

		ImageView currentBis = null;

		switch(getFace()){
			case E :
				currentBis = face;
				break;

			case S :
				currentBis = droite;
				break;

			case N:
				currentBis = gauche;
				break;
			case W:
				currentBis = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().add(currentBis);
		current = currentBis;


		System.out.println(getFace());

	}

	@Override
	public void backward() {

		StackPane sp;
		ImageView currentBis = null;

		switch(getFace()){
			case E :
				currentBis = face;
				break;

			case S :
				currentBis = droite;
				break;

			case N:
				currentBis = gauche;
				break;
			case W:
				currentBis = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().add(currentBis);
		current = currentBis;



	}

	@Override
	public void turnL() {

		StackPane sp;
		ImageView currentBis = null;


		switch(getFace()){
			case E :
				currentBis = face;
				break;

			case S :
				currentBis = droite;
				break;

			case N:
				currentBis = gauche;
				break;
			case W:
				currentBis = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().remove(1);
		current = currentBis;
		sp.getChildren().add(current);


	}

	@Override
	public void turnR() {

		StackPane sp;
		ImageView currentBis = null;


		switch(getFace()){
			case E :
				currentBis = face;
				break;

			case S :
				currentBis = droite;
				break;

			case N:
				currentBis = gauche;
				break;
			case W:
				currentBis = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().remove(1);
		current = currentBis;
		sp.getChildren().add(current);



	}

	@Override
	public void strafeL() {

		StackPane sp;

		ImageView currentBis = null;

		switch(getFace()){
			case E :
				currentBis = face;
				break;

			case S :
				currentBis = droite;
				break;

			case N:
				currentBis = gauche;
				break;
			case W:
				currentBis = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		current = currentBis;
		sp.getChildren().add(current);


	}

	@Override
	public void strafeR() {

		StackPane sp;

		ImageView currentBis= null;

		switch(oldDir){
			case E :
				currentBis = face;
				break;

			case S :
				currentBis = droite;
				break;

			case N:
				currentBis = gauche;
				break;
			case W:
				currentBis = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		current = currentBis;
		sp.getChildren().add(current);
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp, int damage) {
		EnvironmentServiceDecorator dec = (EnvironmentServiceDecorator) env;
		em = (EnvironmentMouvements) dec.getDelegate();
		StackPane sp = (StackPane) em.grille.getChildren().get(0);
		sp.getChildren().add(face);
		current = face;
		cow.init(env, col, row, dir, hp, damage);

	}

	@Override
	public void step() {
		cow.step();

	}

}
