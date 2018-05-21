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
		
		if(getFace()==Dir.E){
			System.out.println(getCol()+" "+getRow());
			sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol());
			sp.getChildren().remove(face);
			sp = (StackPane)em.grille.getChildren().get(getRow()*em.getWidth()+getCol()+1);
			sp.getChildren().add(face);
		}
		
		joueur.forward();
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		joueur.backward();
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		joueur.turnL();
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		joueur.turnR();
	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
		joueur.strafeL();
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		joueur.strafeR();
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
		if(joueur.getLastCom().getValue()==Command.FF){
			this.forward();
		}
		
		if(joueur.getLastCom().getValue()==Command.BB){
			this.forward();
		}
		
		if(joueur.getLastCom().getValue()==Command.RR){
			this.forward();
		}
		if(joueur.getLastCom().getValue()==Command.LL){
			this.forward();
		}
		if(joueur.getLastCom().getValue()==Command.TR){
			this.forward();
		}
		if(joueur.getLastCom().getValue()==Command.TL){
			this.forward();
		}
		
		joueur.step();
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
