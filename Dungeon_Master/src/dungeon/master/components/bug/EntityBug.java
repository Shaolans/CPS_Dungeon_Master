package dungeon.master.components.bug;

import dungeon.master.enumerations.Dir;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;

public abstract class EntityBug extends MobBug implements EntityService {
	private int hp;
	private int damage = 0;
	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp, int damage) {
		super.init(env, col, row, dir);
		this.hp = hp;
		this.damage = damage;

	}
	
	@Override
	public int getHp() {
		return hp;
	}
	
	@Override
	public void setHp(int hp) {
		this.hp = hp;
		
	}
	
	@Override
	public void attack() {
		if(getFace() == Dir.E && getRow()+1 < getEnv().getHeight() && getEnv().getCellContent(getCol(), getRow()+1).getValue() != null) {
			getEnv().getCellContent(getCol(), getRow()+1).getValue().setHp(getEnv().getCellContent(getCol(), getRow()+1).getValue().getHp()-getDamage());
		}
		
		if(getFace() == Dir.W && getRow()-1 >0 && getEnv().getCellContent(getCol(), getRow()-1).getValue() != null) {
			getEnv().getCellContent(getCol(), getRow()-1).getValue().setHp(getEnv().getCellContent(getCol(), getRow()-1).getValue().getHp()-getDamage());
		}
		
		if(getFace() == Dir.S && getCol()-1 >= 0 && getEnv().getCellContent(getCol()-1, getRow()).getValue() != null) {
			getEnv().getCellContent(getCol()-1, getRow()).getValue().setHp(getEnv().getCellContent(getCol()-1, getRow()).getValue().getHp()-getDamage());
		}
		
		if(getFace() == Dir.N && getCol()+1 < getEnv().getWidth() && getEnv().getCellContent(getCol()+1, getRow()).getValue() != null) {
			getEnv().getCellContent(getCol()+1, getRow()).getValue().setHp(getEnv().getCellContent(getCol()+1, getRow()).getValue().getHp()-getDamage());
		}
	}
	
	@Override
	public int getDamage() {
		return damage;
	}

}
