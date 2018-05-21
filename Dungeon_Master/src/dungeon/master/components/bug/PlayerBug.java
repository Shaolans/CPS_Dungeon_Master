package dungeon.master.components.bug;

import java.util.HashSet;
import java.util.Set;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;
import dungeon.master.enumerations.Option;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.services.EntityService;
import dungeon.master.services.MobService;
import dungeon.master.services.PlayerService;

public class PlayerBug extends EntityBug implements PlayerService {
	private Option<Command> last;
	private boolean foundTreasure;
	
	@Override
	public Option<Command> getLastCom() {
		return last;
	}

	
	@Override
	public void init(dungeon.master.services.EnvironmentService env, int col, int row, dungeon.master.enumerations.Dir dir, int hp, int damage) {
		super.init(env, col, row, dir, hp, damage);
		
		last = new Option<Command>();
	};
	@Override
	public Option<EntityService> getContent(int col, int row) {
		return env.getCellContent(getCol()+col, getRow()+row);
	}

	@Override
	public Cell getNature(int col, int row) {
		return env.getCellNature(getCol()+col, getRow()+row);
	}

	@Override
	public boolean isViewable(int col, int row) {
		if(!(col+this.col < env.getWidth() && col+this.col >= 0 && row+this.row < env.getHeight() && row+this.row < env.getHeight())) {
			return false;
		}
		
		Set<Cell> notviewable = new HashSet<>();
		notviewable.add(Cell.WLL);
		notviewable.add(Cell.DWC);
		notviewable.add(Cell.DNC);
		
		if((col <= 1 && -1 <= col) && (row <= 1 && row >= -1)){
			return true;
		}
		
		if(col == -1 && row == 2){
			if(!notviewable.contains(getNature(-1, 1))){
				return true;
			}
			return false;
		}
		if(col == 0 && row == 2){
			
			if(!notviewable.contains(getNature(0, 1))){
				return true;
			}
			return false;
		}
		if(col == 1 && row == 2){
			if(!notviewable.contains(getNature(1, 1))){
				return true;
			}
			return false;
		}
		if(col == -1 && row == 3){
			if(!notviewable.contains(getNature(-1, 2))){
				return true;
			}
			return false;
		}
		if(col == 0 && row == 3){
			if(!notviewable.contains(getNature(0, 2)) && isViewable(0, 2)){
				return true;
			}
			return false;
		}
		if(col == 1 && row == 3){
			if(!notviewable.contains(getNature(1, 2))){
				return true;
			}
			return false;
		}
		
		
		return false;
	}

	@Override
	public void step() {
		if(last == null) return;
		switch(last.getValue()){
		case BB:
			forward();
			break;
		case FF:
			backward();
			break;
		case RR:
			strafeL();
			break;
		case LL:
			strafeR();
			break;
		case TR:
			turnL();
			break;
		case TL:
			turnR();
			break;
		default:
		}
		setLastCom(Command.NONE);

	}

	@Override
	public void setLastCom(Command cmd) {
		last = new Option<Command>(cmd);
	}


	@Override
	public void openDoor() {
		if(getFace() == Dir.N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(),getRow()+1) == Cell.DWC) {
			getEnv().openDoor(getCol(), getRow()+1);
		}
		
		if(getFace() == Dir.N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(),getRow()+1) == Cell.DNC) {
			getEnv().openDoor(getCol(), getRow()+1);
		}
		
		if(getFace() == Dir.S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(),getRow()-1) == Cell.DWC) {
			getEnv().openDoor(getCol(), getRow()-1);
		}
		
		if(getFace() == Dir.S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(),getRow()-1) == Cell.DNC) {
			getEnv().openDoor(getCol(), getRow()-1);
		}
		
		if(getFace() == Dir.E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1,getRow()) == Cell.DWC) {
			getEnv().openDoor(getCol()+1, getRow());
		}
		
		if(getFace() == Dir.E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1,getRow()) == Cell.DNC) {
			getEnv().openDoor(getCol()+1, getRow());
		}
		
		if(getFace() == Dir.W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1,getRow()) == Cell.DWC) {
			getEnv().openDoor(getCol()-1, getRow());
		}
		
		if(getFace() == Dir.W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1,getRow()) == Cell.DNC) {
			getEnv().openDoor(getCol()-1, getRow());
		}
		
	}


	@Override
	public void closeDoor() {
		if(getFace() == Dir.N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(),getRow()+1) == Cell.DWO) {
			getEnv().closeDoor(getCol(), getRow()+1);
		}
		
		if(getFace() == Dir.N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(),getRow()+1) == Cell.DNO) {
			getEnv().closeDoor(getCol(), getRow()+1);
		}
		
		if(getFace() == Dir.S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(),getRow()-1) == Cell.DWO) {
			getEnv().closeDoor(getCol(), getRow()-1);
		}
		
		if(getFace() == Dir.S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(),getRow()-1) == Cell.DNO) {
			getEnv().closeDoor(getCol(), getRow()-1);
		}
		
		if(getFace() == Dir.E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1,getRow()) == Cell.DWO) {
			getEnv().closeDoor(getCol()+1, getRow());
		}
		
		if(getFace() == Dir.E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1,getRow()) == Cell.DNO) {
			getEnv().closeDoor(getCol()+1, getRow());
		}
		
		if(getFace() == Dir.W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1,getRow()) == Cell.DWO) {
			getEnv().closeDoor(getCol()-1, getRow());
		}
		
		if(getFace() == Dir.W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1,getRow()) == Cell.DNO) {
			getEnv().closeDoor(getCol()-1, getRow());
		}
		
	}


	@Override
	public boolean foundTreasure() {
		return foundTreasure;
	}


	@Override
	public void setFoundTreasure(boolean b) {
		foundTreasure = b;
		
	}


	@Override
	public void pickItem() {
		if(getFace() == Dir.N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(),getRow()+1) == Cell.TRS) {
			getEnv().setNature(getCol(), getRow()+1, Cell.EMP);
		}
		
		if(getFace() == Dir.S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(),getRow()-1) == Cell.TRS) {
			getEnv().setNature(getCol(), getRow()-1, Cell.EMP);
		}

		
		if(getFace() == Dir.E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1,getRow()) == Cell.TRS) {
			getEnv().setNature(getCol()+1, getRow(), Cell.EMP);
		}
		

		if(getFace() == Dir.W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1,getRow()) == Cell.TRS) {
			getEnv().setNature(getCol()-1, getRow(), Cell.EMP);
		}
		
		foundTreasure = true;

		
	}



}
