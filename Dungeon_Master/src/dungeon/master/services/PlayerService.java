package dungeon.master.services;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Option;

public interface PlayerService extends EntityService {
	/* Observators */
	public Option<Command> getLastCom();
	
	// \pre col \in {-1,0,1} and y \in {-1,+3}
	public Option<MobService> getContent(int col, int row);
	
	// \pre col \in {-1,0,1} and y \in {-1,+3}
	public Cell getNature(int col, int row);
	
	// \pre col \in {-1,0,1} and y \in {-1,+3}
	public Cell getViewable(int col, int row);
	
	/* Invariants */
	// \inv getFace()==N \implies getContent(u,v) = getEnv().getCellContent(getCol()+u,getRow()+v)
	// \inv getFace()==N \implies getNature(u,v) = getEnv().getCellNature(getCol()+u,getRow()+v)
	// \inv getFace()==S \implies getContent(u,v) = getEnv().getCellContent(getCol()-u,getRow()-v)
	// \inv getFace()==S \implies getNature(u,v) = getEnv().getCellNature(getCol()-u,getRow()-v)
	// \inv getFace()==E \implies getContent(u,v) = getEnv().getCellContent(getCol()+u,getRow()-v)
	// \inv getFace()==E \implies getNature(u,v) = getEnv().getCellNature(getCol()+u,getRow()-v)
	// \inv getFace()==W \implies getContent(u,v) = getEnv().getCellContent(getCol()-u,getRow()+v)
	// \inv getFace()==W \implies getNature(u,v) = getEnv().getCellNature(getCol()-u,getRow()+v)
	
	// \inv \forall u,v \in [-1,1]x[-1,1], !getViewable(u,v)
	
	// \inv getViewable(-1,2) = getNature(-1,1) \not \in {WALL, DWC, DNC}
	// \inv getViewable(0,2) = getNature(0,1) \not \in {WALL, DWC, DNC}
	// \inv getViewable(1,2) = getNature(1,1) \not \in {WALL, DWC, DNC}
	// \inv getViewable(-1,3) = getNature(-1,2) \not \in {WALL, DWC, DNC} and getViewable(-1,2)
	// \inv getViewable(0,3) = getNature(0,2) \not \in {WALL, DWC, DNC} and getViewable(0,2)
	// \inv getViewable(11,3) = getNature(1,2) \not \in {WALL, DWC, DNC} and getViewable(1,2)
	
	/* Operators */
	public void step();
	/* \post getLastCom()@pre = FF \implies step() = forward()
	 * \post getLastCom()@pre = BB \implies step() = backward()
	 * \post getLastCom()@pre = LL \implies step() = StrafeL()
	 * \post getLastCom()@pre = RR \implies step() = StrafeR()
	 * \post getLastCom()@pre = TL \implies step() = TurnLeft()
	 * \post getLastCom()@pre = TR \implies step() = TurnRight()
	 */
}
