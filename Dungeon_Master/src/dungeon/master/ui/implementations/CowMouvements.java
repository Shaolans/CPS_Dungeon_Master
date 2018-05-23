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



		switch(getFace()){
			case E :
				current = face;
				break;

			case S :
				current = droite;
				break;

			case N:
				current = gauche;
				break;
			case W:
				current = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().add(current);


	}

	@Override
	public void backward() {

		StackPane sp;



		switch(getFace()){
			case E :
				current = face;
				break;

			case S :
				current = droite;
				break;

			case N:
				current = gauche;
				break;
			case W:
				current = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().add(current);


	}

	@Override
	public void turnL() {
		StackPane sp;



		switch(getFace()){
			case E :
				current = face;
				break;

			case S :
				current = droite;
				break;

			case N:
				current = gauche;
				break;
			case W:
				current = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().remove(1);
		sp.getChildren().add(current);


	}

	@Override
	public void turnR() {
		StackPane sp;

		switch(getFace()){
			case E :
				current = face;
				break;

			case S :
				current = droite;
				break;

			case N:
				current = gauche;
				break;
			case W:
				current = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().remove(1);
		sp.getChildren().add(current);

	}

	@Override
	public void strafeL() {

		StackPane sp;



		switch(getFace()){
			case E :
				current = face;
				break;

			case S :
				current = droite;
				break;

			case N:
				current = gauche;
				break;
			case W:
				current = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().add(current);


	}

	@Override
	public void strafeR() {
		StackPane sp;



		switch(oldDir){
			case E :
				current = face;
				break;

			case S :
				current = droite;
				break;

			case N:
				current = gauche;
				break;
			case W:
				current = derriere;
				break;
		}

		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(current);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
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
