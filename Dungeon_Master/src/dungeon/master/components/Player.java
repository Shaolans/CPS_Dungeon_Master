package dungeon.master.components;

import java.util.HashSet;
import java.util.Set;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;
import dungeon.master.enumerations.Option;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.services.MobService;
import dungeon.master.services.PlayerService;

public class Player extends Entity implements PlayerService {
	private Option<Command> last;
	
	@Override
	public Option<Command> getLastCom() {
		return last;
	}

	
	@Override
	public void init(dungeon.master.services.EnvironmentService env, int col, int row, dungeon.master.enumerations.Dir dir, int hp) {
		super.init(env, col, row, dir, hp);
		last = new Option<Command>();
	};
	@Override
	public Option<MobService> getContent(int col, int row) {
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
		case FF:
			forward();
			break;
		case BB:
			backward();
			break;
		case LL:
			strafeL();
			break;
		case RR:
			strafeR();
			break;
		case TL:
			turnL();
			break;
		case TR:
			turnR();
			break;
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

}
