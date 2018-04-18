package dungeon.master.services;

import dungeon.master.enumerations.Dir;

public interface EntityService extends MobService {
	
	/* Observators */
	public int getHp();
	
	/* Invariant */
	//NONE
	
	/* Constructors */
	
	/* \pre h > 0
	 * \pre 0 <= col <= env.getWidth()
	 * \pre 0 <= row <= env.getHeight()
	 */
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp);
	/* \post getEnv() = env
	 * \post getCol() = col
	 * \post getRow() = row
	 * \post getFace() = dir
	 * \post getHp() = hp;
	 */
	
}
