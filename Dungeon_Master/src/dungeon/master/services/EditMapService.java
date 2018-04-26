package dungeon.master.services;

import dungeon.master.enumerations.Cell;

public interface EditMapService extends MapService /*refine*/ {
	
	/* Observators */
	
	// \pre getCellNature(x1,y1) != WLL and getCellNature(x2,y2) != WLL
	public boolean isReachable(int x1, int y1, int x2, int y2);
	
	public boolean isReady();
	
	
	/* Invariants */
	/* \inv isReachable(x1,y1,x2,y2) = exists P in Array[int,int], P[0]=(x1,y1) and P[size(P)-1]=(x2,y2)
	 * and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i](s,t)) \implies (u-s)²+(v-t)^2=1
	 * and forall i in [1;size(P)-2], P[i-1]=(u,v) \implies getCellNature(u,v) != WALL
	 * 
	 * \inv isReady() = exists xi,yi,xo,yo in intxintxintxint
	 *  getCellNature(xi,yi) = IN and getCellNature(xo,yo) = OUT
	 * 		 and isReachable(xi,yi,xo,yo)
	 *     	 and forall x,y in intxint, x != xi or y != yi \implies getCellNature(x,y) != IN
	 * 		 and forall x,y in intxint, x != xo or y != yo \implies getCellNature(x,y) != OUT
	 *  forall x,y in int, getCellNature(x,y) in {DNO, DNC} \implies getCellNature(x+1,y) = getCellNature(x-1,y) = EMP and
	 *  getCellNature(x,y-1) = getCellNature(x,y+1) = WLL
	 *  forall x,y in int, getCellNature(x,y) in {DWO, DWC} \implies getCellNature(x+1,y) = getCellNature(x-1,y) = WLL and
	 *  getCellNature(x,y-1) = getCellNature(x,y+1) = EMP
	 */
	
	/* Constructors*/
	//NONE
	
	/* Operators */
	
	// \pre 0<= col < getWidth() and 0<= row < getHeight()
	public void setNature(int col, int row, Cell cell);
	// \post getCellNature(col,row) == cell
	// \post \forall u,v in intxint, u !=x or v !=y \implies getCellNature(u,v) == getCellNature(u,v)@pre
}
