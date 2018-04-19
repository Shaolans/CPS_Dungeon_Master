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
}
