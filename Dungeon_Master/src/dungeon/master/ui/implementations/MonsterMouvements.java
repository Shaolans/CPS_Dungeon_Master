package dungeon.master.ui.implementations;

import java.util.List;

import dungeon.master.decorators.EnvironmentServiceDecorator;
import dungeon.master.enumerations.Dir;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MonsterService;
import dungeon.master.ui.MainWindow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MonsterMouvements implements MonsterService {

	MonsterService monster;
	private ImageView face, faceD, faceG, droite, droiteD, droiteG,
	derriere, derriereD, derriereG, gauche, gaucheD, gaucheG;

	private int oldCol, oldRow;
	private Dir oldDir;
	private EnvironmentMouvements em;
	private ImageView current;

	public MonsterMouvements(MonsterService monster) {

		this.monster = monster;
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
		return monster.getHp();
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return monster.getDamage();
	}

	@Override
	public void attack() {

		monster.attack();
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
		monster.setHp(hp);

	}

	@Override
	public EnvironmentService getEnv() {
		// TODO Auto-generated method stub
		return monster.getEnv();
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return monster.getCol();
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return monster.getRow();
	}

	@Override
	public Dir getFace() {
		// TODO Auto-generated method stub
		return monster.getFace();
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir) {
		EnvironmentServiceDecorator dec = (EnvironmentServiceDecorator) env;
		em = (EnvironmentMouvements) dec.getDelegate();
		StackPane sp = (StackPane) em.grille.getChildren().get(0);
		sp.getChildren().add(face);
		current = face;
		monster.init(env, col, row, dir);

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
		sp.getChildren().remove(current);
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
		sp.getChildren().remove(current);
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
		System.out.println("COW FACE :"+getFace());
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
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp, int damage) {
		EnvironmentServiceDecorator dec = (EnvironmentServiceDecorator) env;
		em = (EnvironmentMouvements) dec.getDelegate();

		current = face;
		monster.init(env, col, row, dir, hp, damage);

	}

	@Override
	public void step() {

		oldRow = getRow();
		oldCol = getCol();
		Dir oldDir = getFace();

		monster.step();

		System.out.println("Monters face "+getFace()+" "+getCol()+" "+getRow());

		if(oldDir==getFace()) {
			switch(getFace()) {
				case E :

					if(getRow()==oldRow && getCol()==oldCol+1)
						this.forward();
					if(getRow()==oldRow && getCol()==oldCol-1)
						this.backward();
					if(getRow()==oldRow-1 && getCol()==oldCol)
						this.strafeR();
					if(getRow()==oldRow+1 && getCol()==oldCol)
						this.strafeL();


					break;

				case W:

					if(getRow()==oldRow && getCol()==oldCol+1)
						this.backward();
					if(getRow()==oldRow && getCol()==oldCol-1)
						this.forward();
					if(getRow()==oldRow-1 && getCol()==oldCol)
						this.strafeL();
					if(getRow()==oldRow+1 && getCol()==oldCol)
						this.strafeR();

					break;
				case N :

					if(getRow()==oldRow && getCol()==oldCol+1)
						this.strafeR();
					if(getRow()==oldRow && getCol()==oldCol-1)
						this.strafeL();
					if(getRow()==oldRow-1 && getCol()==oldCol)
						this.backward();
					if(getRow()==oldRow+1 && getCol()==oldCol)
						this.forward();

					break;
				case S :

					if(getRow()==oldRow && getCol()==oldCol+1)
						this.strafeL();
					if(getRow()==oldRow && getCol()==oldCol-1)
						this.strafeR();
					if(getRow()==oldRow-1 && getCol()==oldCol)
						this.forward();
					if(getRow()==oldRow+1 && getCol()==oldCol)
						this.backward();

					break;

			}

		}
		else {
			switch(getFace()) {
			case E :

				if(oldDir == Dir.N)
					this.turnL();
				else
					this.turnR();


				break;

			case W:

				if(oldDir == Dir.N)
					this.turnR();
				else
					this.turnL();

				break;
			case N :

				if(oldDir == Dir.W)
					this.turnL();
				else
					this.turnR();
				break;
			case S :

				if(oldDir == Dir.W)
					this.turnR();
				else
					this.turnL();

				break;
			}


		}
	}
}
