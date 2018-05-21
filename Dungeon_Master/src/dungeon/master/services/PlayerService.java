package dungeon.master.services;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Option;

public interface PlayerService extends EntityService {
	/* Observators */
	public void setLastCom(Command cmd);
	// \post getLastCom().getValue() == cmd
	
	public Option<Command> getLastCom();
	
	// \pre col \in {-1,0,1} and y \in {-1,+3}
	public Option<EntityService> getContent(int col, int row);

	// \pre col \in {-1,0,1} and y \in {-1,+3}
	public Cell getNature(int col, int row);

	// \pre col \in {-1,0,1} and y \in {-1,+3}
	public boolean isViewable(int col, int row);
	
	public boolean foundTreasure();
	
	/* Invariants */
	// \inv getFace()==N \implies getContent(u,v) = getEnv().getCellContent(getCol()+u,getRow()+v)
	// \inv getFace()==N \implies getNature(u,v) = getEnv().getCellNature(getCol()+u,getRow()+v)
	// \inv getFace()==S \implies getContent(u,v) = getEnv().getCellContent(getCol()-u,getRow()-v)
	// \inv getFace()==S \implies getNature(u,v) = getEnv().getCellNature(getCol()-u,getRow()-v)
	// \inv getFace()==E \implies getContent(u,v) = getEnv().getCellContent(getCol()+u,getRow()-v)
	// \inv getFace()==E \implies getNature(u,v) = getEnv().getCellNature(getCol()+u,getRow()-v)
	// \inv getFace()==W \implies getContent(u,v) = getEnv().getCellContent(getCol()-u,getRow()+v)
	// \inv getFace()==W \implies getNature(u,v) = getEnv().getCellNature(getCol()-u,getRow()+v)

	// \inv \forall u,v \in [-1,1]x[-1,1], !isViewable(u,v)

	// \inv isViewable(-1,2) = getNature(-1,1) \not \in {WALL, DWC, DNC}
	// \inv isViewable(0,2) = getNature(0,1) \not \in {WALL, DWC, DNC}
	// \inv isViewable(1,2) = getNature(1,1) \not \in {WALL, DWC, DNC}
	// \inv isViewable(-1,3) = getNature(-1,2) \not \in {WALL, DWC, DNC} and isViewable(-1,2)
	// \inv isViewable(0,3) = getNature(0,2) \not \in {WALL, DWC, DNC} and isViewable(0,2)
	// \inv isViewable(11,3) = getNature(1,2) \not \in {WALL, DWC, DNC} and isViewable(1,2)
	
	//foundTreasure == \forall i,j \in [0;getWidth()]x[0;getHeight], getEnv().getCellNature(i,j) != Cell.TRS

	/* Operators */
	public void step();
	/* \post getLastCom()@pre = FF \implies step() = forward()
	 * \post getLastCom()@pre = BB \implies step() = backward()
	 * \post getLastCom()@pre = LL \implies step() = StrafeL()
	 * \post getLastCom()@pre = RR \implies step() = StrafeR()
	 * \post getLastCom()@pre = TL \implies step() = TurnLeft()
	 * \post getLastCom()@pre = TR \implies step() = TurnRight()
	 */
	
	public void openDoor();
	/* \post getFace() == N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(), getRow()+1)@pre == Cell.DWC \impl getEnv().getCellNature(getCol(), getRow()+1) == Cell.DNO
	 * \post getFace() == N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(), getRow()+1)@pre == Cell.DNC \impl getEnv().getCellNature(getCol(), getRow()+1) == Cell.DWO
	 * \post getFace() == S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(), getRow()-1)@pre == Cell.DWC \impl getEnv().getCellNature(getCol(), getRow()-1) == Cell.DNO
	 * \post getFace() == S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(), getRow()-1)@pre == Cell.DNC \impl getEnv().getCellNature(getCol(), getRow()-1) == Cell.DWO
	 * \post getFace() == E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1, getRow())@pre == Cell.DWC \impl getEnv().getCellNature(getCol()+1, getRow()) == Cell.DNO
	 * \post getFace() == E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1, getRow())@pre == Cell.DNC \impl getEnv().getCellNature(getCol()+1, getRow()) == Cell.DWO
	 * \post getFace() == W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1, getRow())@pre == Cell.DWC \impl getEnv().getCellNature(getCol()-1, getRow()) == Cell.DNO
	 * \post getFace() == W && getCol()-1 >= 0&& getEnv().getCellNature(getCol()-1, getRow())@pre == Cell.DNC \impl getEnv().getCellNature(getCol()-1, getRow()) == Cell.DWO
	 */
	
	public void closeDoor();
	/* \post getFace() == N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(), getRow()+1)@pre == Cell.DWO \impl getEnv().getCellNature(getCol(), getRow()+1) == Cell.DNC
	 * \post getFace() == N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(), getRow()+1)@pre == Cell.DNO \impl getEnv().getCellNature(getCol(), getRow()+1) == Cell.DWC
	 * \post getFace() == S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(), getRow()-1)@pre == Cell.DWO \impl getEnv().getCellNature(getCol(), getRow()-1) == Cell.DNC
	 * \post getFace() == S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(), getRow()-1)@pre == Cell.DNO \impl getEnv().getCellNature(getCol(), getRow()-1) == Cell.DWC
	 * \post getFace() == E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1, getRow())@pre == Cell.DWO \impl getEnv().getCellNature(getCol()+1, getRow()) == Cell.DNC
	 * \post getFace() == E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1, getRow())@pre == Cell.DNO \impl getEnv().getCellNature(getCol()+1, getRow()) == Cell.DWC
	 * \post getFace() == W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1, getRow())@pre == Cell.DWO \impl getEnv().getCellNature(getCol()-1, getRow()) == Cell.DNC
	 * \post getFace() == W && getCol()-1 >= 0&& getEnv().getCellNature(getCol()-1, getRow())@pre == Cell.DNO \impl getEnv().getCellNature(getCol()-1, getRow()) == Cell.DWC
	 */
	
	public void setFoundTreasure(boolean b);
	// \post foundTreasure() == b
	
	public void pickItem();
	/* \post getFace() == N && getRow()+1 < getEnv().getHeight() && getEnv().getCellNature(getCol(), getRow()+1)@pre == Cell.TRS \impl getEnv().getCellNature(getCol(), getRow()+1) == Cell.EMP && foundTreasure
	 * \post getFace() == S && getRow()-1 >= 0 && getEnv().getCellNature(getCol(), getRow()-1)@pre == Cell.TRS \impl getEnv().getCellNature(getCol(), getRow()-1) == Cell.EMP && foundTreasure
	 * \post getFace() == E && getCol()+1 < getEnv().getWidth() && getEnv().getCellNature(getCol()+1, getRow())@pre == Cell.TRS \impl getEnv().getCellNature(getCol()+1, getRow()) == Cell.EMP && foundTreasure
	 * \post getFace() == W && getCol()-1 >= 0 && getEnv().getCellNature(getCol()-1, getRow())@pre == Cell.TRS \impl getEnv().getCellNature(getCol()-1, getRow()) == Cell.EMP && foundTreasure
	 */
}
