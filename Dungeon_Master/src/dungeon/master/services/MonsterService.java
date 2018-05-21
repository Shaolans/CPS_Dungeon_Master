package dungeon.master.services;

import dungeon.master.enumerations.Dir;

public interface MonsterService extends EntityService {
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
	 */
	
	public void step();
	/* \post getCol()@pre - 1 <= getCol() <= getCol()@pre + 1
	 * \post getRow()@pre - 1 <= getRow() <= getRow()@pre + 1
	 */
}
