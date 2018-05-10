package dungeon.master.services;

import dungeon.master.enumerations.Option;

public interface EnvironmentService extends MapService {
	
	/* Observators */
	public Option<MobService> getCellContent(int x, int y);
	
	/* Operators */
	// \pre getCellContent(col,row) == No (null)
	public void closeDoor(int col, int row);
	// \post getCellNature(col,row)@pre == DWO \implies getCellNature(col,row) == DWC
	// \post getCellNature(col,row)@pre == DNO \implies getCellNature(col,row) == DNC
	
	// \pre 0 <= col < getHeight() and 0 <= row < getWidth()
	public void setCellContent(int col, int row, MobService mob);
	// \post getCellContent(col, row) == Option<Mob>(mob)
	// \post \forall x \in [0;getHeight()-1], \forall y \in [0;getWidth()-] and x!=col and y!=row, getCellContent(x,y) = getCellContent(x,y)@pre
}
