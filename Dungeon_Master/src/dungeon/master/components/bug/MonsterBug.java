package dungeon.master.components.bug;

import dungeon.master.services.MonsterService;
import dungeon.master.services.PlayerService;

public class MonsterBug extends EntityBug implements MonsterService {

	@Override
	public void step() {
		PlayerService player = null;
		for(int i = 0; i < getEnv().getWidth(); i++) {
			for(int j = 0; j < getEnv().getHeight(); j++) {
				if(getEnv().getCellContent(i, j).getValue() instanceof PlayerService) {
					player = (PlayerService)getEnv().getCellContent(i, j).getValue();
					break;
				}
			}
		}
		
		if((getCol()+1 == player.getCol() && getRow() == player.getRow()) || 
				(getCol()-1 == player.getCol() && getRow() == player.getRow()) ||
				(getCol() == player.getCol() && getRow()+1 == player.getRow()) ||
				(getCol() == player.getCol() && getRow()-1 == player.getRow())) {
			return;
		}
		
		int dist = player.getRow()-getRow();
		if(dist > 0) {
			forward();
			return;
		}else if(dist < 0) {
			backward();
			return;
		}
		
		dist = player.getCol()-getCol();
		if(dist > 0) {
			strafeR();
			return;
		}else if(dist < 0) {
			strafeL();
			strafeL();
			strafeL();
			strafeL();
			return;
		}
		

	}

	@Override
	public void attack() {
		if(getRow()+1 < getEnv().getHeight() && (getEnv().getCellContent(getCol(), getRow()+1).getValue() instanceof PlayerService)) {
			getEnv().getCellContent(getCol(), getRow()+1).getValue().setHp(getEnv().getCellContent(getCol(), getRow()+1).getValue().getHp()-getDamage());
		}
		
		if(getRow()-1 >0 && (getEnv().getCellContent(getCol(), getRow()-1).getValue() instanceof PlayerService)) {
			getEnv().getCellContent(getCol(), getRow()-1).getValue().setHp(getEnv().getCellContent(getCol(), getRow()-1).getValue().getHp()-getDamage());
		}
		
		if(getCol()-1 >= 0 && (getEnv().getCellContent(getCol()-1, getRow()).getValue() instanceof PlayerService)) {
			getEnv().getCellContent(getCol()-1, getRow()).getValue().setHp(getEnv().getCellContent(getCol()-1, getRow()).getValue().getHp()-getDamage());
		}
		
		if(getCol()+1 < getEnv().getWidth() && (getEnv().getCellContent(getCol()+1, getRow()).getValue() instanceof PlayerService)) {
			getEnv().getCellContent(getCol()-1, getRow()).getValue().setHp(getEnv().getCellContent(getCol()-1, getRow()).getValue().getHp()-getDamage());
		}
	}
}
