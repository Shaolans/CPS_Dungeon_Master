package dungeon.master.services;

import dungeon.master.enumerations.Dir;

public interface MobService {
	

	/* Observators */
	
	public EnvironmentService getEnv();
	
	public int getCol();
	
	public int getRow();
	
	public Dir getFace();
	
	
	
	/* Invariants */
	
	// \inv 0 <= getCol() <= getEnv().getWidth()
	// \inv 0 <= getRow() <= getEnv().getHeight()
	// \inv getEnv().getCellNature(getRow(),getCol()) \not \in {WLL, DNC, DWC}
	
	
	
	/* Constructors */
	
	/* \pre 0 <= col < getEnv().getWidth()
	 * \pre 0 <= row < getEnv().getHeight()
	 */
	public void init(EnvironmentService env, int col, int row, Dir dir);
	/* \post getEnv() = env
	 * \post getCol() = col
	 * \post getRow() = row
	 * \post getFace() = dir
	 */
	
	
	
	/* Operators */
	
	public void forward();
	// \post l'obervateur face ne change pas ici
	/* \post getFace()@pre == N \implies
	 * getEnv().getCellNature(getCol()@pre, Row()@pre+1) \in {EMP, DNO}
	 * and getRow()@pre+1 < getEnv().getHeight()
	 * and getEnv().getCellContent(getCol()@pre, Row()@pre+1) == No
	 * \implies
	 * getRow() = getRow()@pre + 1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == N \implies
	 * getEnv().getCellNature(getCol()@pre, Row()@pre+1) \not \in {EMP, DNO}
	 * or getRow()@pre+1 >= getEnv().getHeight()
	 * or getEnv().getCellContent(getCol()@pre, Row()@pre+1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == E \implies
	 *  getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()@pre + 1 < getEnv().getWidth()
	 * and getEnv().getCellContent(getCol()@pre+1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre + 1
	 * 
	 * \post getFace()@pre == E \implies
	 *  getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \not \in {EMP, DWO}
	 * or getCol()@pre + 1 >= getEnv().getWidth()
	 * or getEnv().getCellContent(getCol()@pre+1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == S \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \in {EMP, DNO}
	 * and getRow()@pre-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre-1) == No
	 * \implies
	 * getRow() = getRow()@pre - 1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == S \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \not \in {EMP, DNO}
	 * and getRow()@pre-1 < 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre-1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == W \implies
	 *  getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()@pre-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre-1
	 * 
	 * \post getFace()@pre == W \implies
	 *  getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \not \in {EMP, DWO}
	 * and getCol()@pre-1 < 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 */
	
	public void backward();
	/* * \post getFace()@pre == N \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \in {EMP, DNO}
	 * and getRow()@pre-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre-1) == No
	 * \implies
	 * getRow() = getRow()@pre - 1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == N \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \not \in {EMP, DNO}
	 * and getRow()@pre-1 < 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre-1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == E \implies
	 *  getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()@pre-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre-1
	 * 
	 * \post getFace()@pre == E \implies
	 *  getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \not \in {EMP, DWO}
	 * and getCol()@pre-1 < 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == S \implies
	 * getEnv().getCellNature(getCol()@pre, Row()@pre+1) \in {EMP, DNO}
	 * and getRow()@pre+1 < getEnv().getHeight()
	 * and getEnv().getCellContent(getCol()@pre, Row()@pre+1) == No
	 * \implies
	 * getRow() = getRow()@pre + 1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == S \implies
	 * getEnv().getCellNature(getCol()@pre, Row()@pre+1) \not \in {EMP, DNO}
	 * or getRow()@pre+1 >= getEnv().getHeight()
	 * or getEnv().getCellContent(getCol()@pre, Row()@pre+1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre == W \implies
	 *  getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()@pre + 1 < getEnv().getWidth()
	 * and getEnv().getCellContent(getCol()@pre+1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre + 1
	 * 
	 * \post getFace()@pre == W \implies
	 *  getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \not \in {EMP, DWO}
	 * or getCol()@pre + 1 >= getEnv().getWidth()
	 * or getEnv().getCellContent(getCol()@pre+1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 */
	
	public void turnL();
	/* \post getFace()@pre==N \implies getFace()==W
	 * \post getFace()@pre==W \implies getFace()==S
	 * \post getFace()@pre==S \implies getFace()==E
	 * \post getFace()@pre==E \implies getFace()==N
	 * \post getCol() = getCol()@pre
	 * \post getRow() = getRow()@pre
	 * \post getEnv() = getEnv()@pre
	 */
	
	public void turnR();
	/* \post getFace()@pre==N \implies getFace()==E
	 * \post getFace()@pre==W \implies getFace()==N
	 * \post getFace()@pre==S \implies getFace()==W
	 * \post getFace()@pre==E \implies getFace()==S
	 * \post getCol() = getCol()@pre
	 * \post getRow() = getRow()@pre
	 * \post getEnv() = getEnv()@pre
	 */
	
	public void strafeL();
	/* \post getFace()@pre==S \implies
	 * getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()+1 < getEnv().getWidth()
	 * and getEnv().getCellContent(getCol()@pre+1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre +1
	 * 
	 * \post getFace()@pre==S \implies
	 * getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \not \in {EMP, DWO}
	 * and getCol()+1 >= getEnv().getWidth()
	 * and getEnv().getCellContent(getCol()@pre+1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==E \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre+1) \in {EMP, DNO}
	 * and getRow()+1 < getEnv().getHeight()
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) == No
	 * \implies
	 * getRow() = getRow()@pre+1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==E \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre+1) \not \in {EMP, DNO}
	 * and getRow()+1 >= getEnv().getHeight()
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==N \implies
	 * getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre-1
	 * 
	 * \post getFace()@pre==N \implies
	 * getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \not \in {EMP, DWO}
	 * and getCol()-1 < 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==W \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \in {EMP, DNO}
	 * and getRow()-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) == No
	 * \implies
	 * getRow() = getRow()@pre-1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==W \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \not \in {EMP, DNO}
	 * and getRow()-1 < 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 */
	
	public void strafeR();
	/* \post getFace()@pre==N \implies
	 * getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()+1 < getEnv().getWidth()
	 * and getEnv().getCellContent(getCol()@pre+1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre +1
	 * 
	 * \post getFace()@pre==N \implies
	 * getEnv().getCellNature(getCol()@pre+1, getRow()@pre) \not \in {EMP, DWO}
	 * and getCol()+1 >= getEnv().getWidth()
	 * and getEnv().getCellContent(getCol()@pre+1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==W \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre+1) \in {EMP, DNO}
	 * and getRow()+1 < getEnv().getHeight()
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) == No
	 * \implies
	 * getRow() = getRow()@pre+1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==W \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre+1) \not \in {EMP, DNO}
	 * and getRow()+1 >= getEnv().getHeight()
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==S \implies
	 * getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \in {EMP, DWO}
	 * and getCol()-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) == No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre-1
	 * 
	 * \post getFace()@pre==S \implies
	 * getEnv().getCellNature(getCol()@pre-1, getRow()@pre) \not \in {EMP, DWO}
	 * and getCol()-1 < 0
	 * and getEnv().getCellContent(getCol()@pre-1, getRow()@pre) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==E \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \in {EMP, DNO}
	 * and getRow()-1 >= 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) == No
	 * \implies
	 * getRow() = getRow()@pre-1 and getCol() = getCol()@pre
	 * 
	 * \post getFace()@pre==E \implies
	 * getEnv().getCellNature(getCol()@pre, getRow()@pre-1) \not \in {EMP, DNO}
	 * and getRow()-1 < 0
	 * and getEnv().getCellContent(getCol()@pre, getRow()@pre+1) != No
	 * \implies
	 * getRow() = getRow()@pre and getCol() = getCol()@pre
	 */
}
