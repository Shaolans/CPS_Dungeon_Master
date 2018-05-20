package dungeon.master.components;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Dir;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class Mob implements MobService {
	protected EnvironmentService env;
	protected int col;
	protected int row;
	protected Dir face;
	
	@Override
	public EnvironmentService getEnv() {
		return env;
	}

	@Override
	public int getCol() {
		return col;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public Dir getFace() {
		return face;
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir) {
		this.env = env;
		this.row = row;
		this.col = col;
		this.face = dir;
	}

	@Override
	public void forward() {
		if(face == Dir.N &&
				row+1 < env.getHeight() &&
				env.getCellContent(col, row+1).getValue() == null &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO || env.getCellNature(col, row+1) == Cell.OUT)){
			
			row++;
			env.setCellContent(col, row, env.getCellContent(col, row-1).getValue());
			env.setCellContent(col, row-1, null);
			
		}
		
		if(face == Dir.E &&
				col+1 < env.getWidth() &&
				env.getCellContent(col+1, row).getValue() == null &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO || env.getCellNature(col+1, row) == Cell.OUT)){
			col++;
			env.setCellContent(col, row, env.getCellContent(col-1, row).getValue());
			env.setCellContent(col-1, row, null);
		}
		
		if(face == Dir.S &&
				row-1 >= 0 &&
				env.getCellContent(col, row-1).getValue() == null &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO || env.getCellNature(col, row-1) == Cell.OUT)){
			row--;
			env.setCellContent(col, row, env.getCellContent(col, row+1).getValue());
			env.setCellContent(col, row+1, null);
		}
		
		if(face == Dir.W &&
				col-1 >= 0 &&
				env.getCellContent(col-1, row).getValue() == null &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO || env.getCellNature(col-1, row) == Cell.OUT)){
			col--;
			env.setCellContent(col, row, env.getCellContent(col+1, row).getValue());
			env.setCellContent(col+1, row, null);
		}
		
		

	}

	@Override
	public void backward() {
		if(face == Dir.S &&
				row+1 < env.getHeight() &&
				env.getCellContent(col, row+1).getValue() == null &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO || env.getCellNature(col, row+1) == Cell.OUT)){
			row++;
			env.setCellContent(col, row, env.getCellContent(col, row-1).getValue());
			env.setCellContent(col, row-1, null);
		}
		
		if(face == Dir.W &&
				col+1 < env.getWidth() &&
				env.getCellContent(col+1, row).getValue() == null &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO || env.getCellNature(col+1, row) == Cell.OUT)){
			col++;
			env.setCellContent(col, row, env.getCellContent(col-1, row).getValue());
			env.setCellContent(col-1, row, null);
		}
		
		if(face == Dir.N &&
				row-1 >= 0 &&
				env.getCellContent(col, row-1).getValue() == null &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO || env.getCellNature(col, row-1) == Cell.OUT)){
			row--;
			env.setCellContent(col, row, env.getCellContent(col, row+1).getValue());
			env.setCellContent(col, row+1, null);
		}
		
		if(face == Dir.E &&
				col-1 >= 0 &&
				env.getCellContent(col-1, row).getValue() == null &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO || env.getCellNature(col-1, row) == Cell.OUT)){
			col--;
			env.setCellContent(col, row, env.getCellContent(col+1, row).getValue());
			env.setCellContent(col+1, row, null);
		}
	}

	@Override
	public void turnL() {
		if(face == Dir.N) { face = Dir.W; return; }
		if(face == Dir.W) { face = Dir.S; return; }
		if(face == Dir.S) { face = Dir.E; return; }
		if(face == Dir.E) { face = Dir.N; return; }
	}

	@Override
	public void turnR() {
		if(face == Dir.N) { face = Dir.E; return; }
		if(face == Dir.W) { face = Dir.N; return; }
		if(face == Dir.S) { face = Dir.W; return; }
		if(face == Dir.E) { face = Dir.S; return; }
	}

	@Override
	public void strafeL() {
		if(face == Dir.S &&
				col+1 < env.getWidth() &&
				env.getCellContent(col+1, row).getValue() == null &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO || env.getCellNature(col+1, row) == Cell.OUT)){
			col++;
			env.setCellContent(col, row, env.getCellContent(col-1, row).getValue());
			env.setCellContent(col-1, row, null);
		}
		
		if(face == Dir.E &&
				row+1 < env.getHeight() &&
				env.getCellContent(col, row+1).getValue() == null &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO || env.getCellNature(col, row+1) == Cell.OUT)){
			row++;
			env.setCellContent(col, row, env.getCellContent(col, row-1).getValue());
			env.setCellContent(col, row-1, null);
		}
		
		if(face == Dir.N &&
				col-1 >= 0 &&
				env.getCellContent(col-1, row).getValue() == null &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO || env.getCellNature(col-1, row) == Cell.OUT)){
			col--;
			env.setCellContent(col, row, env.getCellContent(col+1, row).getValue());
			env.setCellContent(col+1, row, null);
		}
		
		if(face == Dir.W &&
				row-1 >= 0 &&
				env.getCellContent(col, row-1).getValue() == null &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO || env.getCellNature(col, row-1) == Cell.OUT)){
			row--;
			env.setCellContent(col, row, env.getCellContent(col, row+1).getValue());
			env.setCellContent(col, row+1, null);
		}
	}

	@Override
	public void strafeR() {
		if(face == Dir.N &&
				col+1 < env.getWidth() &&
				env.getCellContent(col+1, row).getValue() == null &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO || env.getCellNature(col+1, row) == Cell.OUT)){
			col++;
			env.setCellContent(col, row, env.getCellContent(col-1, row).getValue());
			env.setCellContent(col-1, row, null);
		}
		
		if(face == Dir.W &&
				row+1 < env.getHeight() &&
				env.getCellContent(col, row+1).getValue() == null &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO || env.getCellNature(col, row+1) == Cell.OUT)){
			row++;
			env.setCellContent(col, row, env.getCellContent(col, row-1).getValue());
			env.setCellContent(col, row-1, null);
		}
		
		if(face == Dir.S &&
				col-1 >= 0 &&
				env.getCellContent(col-1, row).getValue() == null &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO || env.getCellNature(col-1, row) == Cell.OUT)){
			col--;
			env.setCellContent(col, row, env.getCellContent(col+1, row).getValue());
			env.setCellContent(col+1, row, null);
		}
		
		if(face == Dir.E &&
				row-1 >= 0 &&
				env.getCellContent(col, row-1).getValue() == null &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO || env.getCellNature(col, row-1) == Cell.OUT)){
			row--;
			env.setCellContent(col, row, env.getCellContent(col, row+1).getValue());
			env.setCellContent(col, row+1, null);
		}
	}

}
