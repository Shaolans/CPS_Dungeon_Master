package dungeon.master.services;

import dungeon.master.enumerations.Cell;

public interface MapService {
	
	/* Observators */
	
	// \const
	public int getHeight();
	
	// \const
	public int getWidth();
	
	// \pre 0<=x<getWidth() and 0<=y<getHeigth()
	public Cell getCellNature(int x, int y);
	
	
	/* Invariants */
	// NONE
	
	
	/* Constructors */
	
	
	// \pre 0<w and 0<h
	
	public void init(int w, int h);
	
	// \post getHeigth()==h and getWidth()==w
	
	
	/* Operators */
	
	
	// \pre getCellNature(x,y) in {DNC, DWC}
	
	public void openDoor(int x, int y);
	
	// \post getCellNature(x,y)@pre=DWC \impl getCellNature(x,y)=DWO
	// \post getCellNature(x,y)@pre=DNC \impl getCellNature(x,y)=DNO
	/* \post \forall u in [0, getWidth()-1] \forall v in [0, getHeight()-1] \with (u!=x or v!=y)
	 	\impl getCellNature(x,y) = getCellNature(x,y)@pre		*/
	
	
	// \pre getCellNature(x, y) in {DNO, DWO}
	
	public void closeDoor(int x, int y);
	
	// \post getCellNature(x,y)@pre=DWO \impl getCellNature(x,y)=DWC
	// \post getCellNature(x,y)@pre=DNO \impl getCellNature(x,y)=DNC
	/* \post \forall u in [0, getWidth()-1] \forall v in [0, getHeight()-1] \with (u!=x or v!=y)
	 	\impl getCellNature(x,y) = getCellNature(x,y)@pre		*/
	
	
	
	//permet les tests
	public Cell[][] getArray();
	
	
}
