package dungeon.master.ui.implementations;

import java.util.List;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;
import dungeon.master.enumerations.Option;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;
import dungeon.master.services.PlayerService;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PlayerMouvements implements PlayerService {
	
	private PlayerService joueur ;
	private ImageView face, faceD, faceG, droite, droiteD, droiteG,
		derriere, derriereD, derriereG, gauche, gaucheD, gaucheG;
	private ImageView current;
	
	private int oldCol, oldRow;
	private Dir oldDir;
	private Command oldCommand;
	
	public PlayerMouvements(PlayerService p, List<ImageView> mouvements){
		joueur = p;
		decouperMouvement(mouvements);
	}
	
	public void decouperMouvement(List<ImageView> moves){
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
		
		return joueur.getHp();
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp) {
		joueur.init(env, col, row, dir, hp);
	}

	@Override
	public EnvironmentService getEnv() {
		// TODO Auto-generated method stub
		return joueur.getEnv();
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return joueur.getCol();
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return joueur.getRow();
	}

	@Override
	public Dir getFace() {
		// TODO Auto-generated method stub
		return joueur.getFace();
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir) {
		
		EnvironmentMouvements em = (EnvironmentMouvements) env;
		
		StackPane sp = (StackPane) em.grille.getChildren().get(0);
		sp.getChildren().add(face);
		current = face;
		
		joueur.init(env, col, row, dir);
	}

	@Override
	public void forward() {
		
		EnvironmentMouvements em = (EnvironmentMouvements)getEnv();
		StackPane sp;
		
		ImageView current = null ;
		
		switch(oldDir){
			case E :
				current = face;
				break;
			
			case N :
				current = droite;
				break;
			
			case S:
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
		
		EnvironmentMouvements em = (EnvironmentMouvements)getEnv();
		StackPane sp;
		
		ImageView current = null ;
		
		switch(oldDir){
			case E :
				current = face;
				break;
			
			case N :
				current = droite;
				break;
			
			case S:
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
		EnvironmentMouvements em = (EnvironmentMouvements)getEnv();
		StackPane sp;
		
		ImageView current = null ;
		
		switch(getFace()){
			case E :
				current = face;
				break;
			
			case N :
				current = droite;
				break;
			
			case S:
				current = gauche;
				break;
			case W:
				current = derriere;
				break;
		}
		
		sp = (StackPane)em.grille.getChildren().get(oldRow*em.getWidth()+oldCol);
		sp.getChildren().remove(1);
		sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
		sp.getChildren().add(current);
		
		
	}

	@Override
	public void turnR() {
		EnvironmentMouvements em = (EnvironmentMouvements)getEnv();
		StackPane sp;
		
		ImageView current = null ;
		
		switch(getFace()){
			case E :
				current = face;
				break;
			
			case N :
				current = droite;
				break;
			
			case S:
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
	public void strafeL() {
		EnvironmentMouvements em = (EnvironmentMouvements)getEnv();
		StackPane sp;
		
		ImageView current = null ;
		
		switch(oldDir){
			case E :
				current = face;
				break;
			
			case N :
				current = droite;
				break;
			
			case S:
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
		EnvironmentMouvements em = (EnvironmentMouvements)getEnv();
		StackPane sp;
		
		ImageView current = null ;
		
		switch(oldDir){
			case E :
				current = face;
				break;
			
			case N :
				current = droite;
				break;
			
			case S:
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
	public void setLastCom(Command cmd) {
		// TODO Auto-generated method stub
		joueur.setLastCom(cmd);
	}

	@Override
	public Option<Command> getLastCom() {
		// TODO Auto-generated method stub
		return joueur.getLastCom();
	}

	@Override
	public Option<MobService> getContent(int col, int row) {
		// TODO Auto-generated method stub
		return joueur.getContent(col, row);
	}

	@Override
	public Cell getNature(int col, int row) {
		// TODO Auto-generated method stub
		return joueur.getNature(col, row);
	}

	@Override
	public boolean isViewable(int col, int row) {
		// TODO Auto-generated method stub
		return joueur.isViewable(col, row);
	}

	@Override
	public void step() {
		
		oldCol = getCol();
		oldRow = getRow();
		oldDir = getFace();
		oldCommand = joueur.getLastCom().getValue();
		
		joueur.step();
		
		if(oldCommand==Command.FF){
			this.forward();
		}
		
		if(oldCommand==Command.BB){
			this.backward();
		}
		
		if(oldCommand==Command.LL){
			this.strafeL();
		}
		if(oldCommand==Command.RR){
			this.strafeR();
		}
		if(oldCommand==Command.TR){
			this.turnL();
		}
		if(oldCommand==Command.TL){
			this.turnR();
		}

	}

	@Override
	public void openDoor() {
		joueur.openDoor();
		
	}

	@Override
	public void closeDoor() {
		joueur.closeDoor();
		
	}

}
