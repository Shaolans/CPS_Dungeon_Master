package dungeon.master.services;

public interface EditMapService extends MapService /*refine*/ {
	
	/* Observators */
	
	// \pre getCellNature(x1,y1) != WLL and getCellNature(x2,y2) != WLL
	public boolean isReachable(int x1, int y1, int x2, int y2);
	
	public boolean isReady();
	
	
	/* Invariants */
	
	/* Constructors*/
	//NONE
	
	/* Operators */
}
