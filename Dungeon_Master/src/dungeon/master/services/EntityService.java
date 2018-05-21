package dungeon.master.services;

import dungeon.master.enumerations.Dir;

public interface EntityService extends MobService {
	
	/* Observators */
	public int getHp();
	
	public int getDamage();
	
	/* Invariant */
	//NONE
	
	/* Constructors */
	
	/* \pre h > 0
	 * \pre damage >= 0
	 * \pre 0 <= col <= env.getWidth()
	 * \pre 0 <= row <= env.getHeight()
	 */
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp, int damage);
	/* \post getEnv() = env
	 * \post getCol() = col
	 * \post getRow() = row
	 * \post getFace() = dir
	 * \post getHp() = hp;
	 * \post getDamage() = damage;
	 */
	
	/* Operators */
	public void step();
	
	public void attack();
	/* \post getFace() == N && getRow()+1 < getEnv().getHeight() && getEnv().getCellContent(getCol(), getRow()+1).getValue()@pre != null 
	 * \impl getEnv().getCellContent(getCol(), getRow()+1).getValue().getHp() == getEnv().getCellContent(getCol(), getRow()+1).getValue().getHp()@pre - getDamage()
	 * \post getFace() == S && getRow()-1 >= 0 && getEnv().getCellContent(getCol(), getRow()-1).getValue()@pre != null 
	 * \impl getEnv().getCellContent(getCol(), getRow()-1).getValue().getHp() = getEnv().getCellContent(getCol(), getRow()-1).getValue().getHp()@pre - getDamage()
	 * \post getFace() == E && getCol()+1 < getEnv().getWidth() && getEnv().getCellContent(getCol()+1, getRow()).getValue()@pre != null
	 * \impl getEnv().getCellContent(getCol()+1, getRow()).getValue().getHp() == getEnv().getCellContent(getCol()+1, getRow()).getValue().getHp()@pre - getDamage()
	 * \post getFace() == W && getCol()-1 >= 0 && getEnv().getCellContent(getCol()-1, getRow()).getValue()@pre != null
	 * \impl getEnv().getCellContent(getCol()-1, getRow()).getValue().getHp() == getEnv().getCellContent(getCol()-1, getRow()).getValue().getHp()@pre - getDamage()
	 */
	
	public void setHp(int hp);
	/* \post getHp() = hp */
}
