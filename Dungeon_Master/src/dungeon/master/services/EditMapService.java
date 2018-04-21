package dungeon.master.services;

import dungeon.master.enumerations.Cell;

public interface EditMapService extends MapService /*refine*/ {
	
	/* Observators */
	
	// \pre getCellNature(x1,y1) != WLL and getCellNature(x2,y2) != WLL
	public boolean isReachable(int x1, int y1, int x2, int y2);
	
	public boolean isReady();
	
	
	/* Invariants */
	
	/* Constructors*/
	//NONE
	
	/* Operators */
	
	// \pre 0<= col < getWidth() and 0<= row < getHeight()
	public void setNature(int col, int row, Cell cell);
	// \post getCellNature(col,row) == cell
	// \post \forall u,v in intxint, u !=x or v !=y \implies getCellNature(u,v) == getCellNature(u,v)@pre
}
