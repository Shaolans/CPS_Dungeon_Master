package dungeon.master.components;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Dir;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class Mob implements MobService {
	private EnvironmentService env;
	private int col;
	private int row;
	private Dir face;
	
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
	public void init(EnvironmentService env, int row, int col, Dir dir) {
		this.env = env;
		this.row = row;
		this.col = col;
		this.face = dir;
	}

	@Override
	public void forward() {
		if(face == Dir.N &&
				row+1 < env.getHeight() &&
				!env.getCellContent(col, row+1) &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO)){
			row++;
		}
		
		if(face == Dir.E &&
				col+1 < env.getWidth() &&
				!env.getCellContent(col+1, row) &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO)){
			col++;
		}
		
		if(face == Dir.S &&
				row-1 >= 0 &&
				!env.getCellContent(col, row-1) &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO)){
			row--;
		}
		
		if(face == Dir.W &&
				col-1 >= 0 &&
				!env.getCellContent(col-1, row) &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO)){
			col--;
		}
		
		

	}

	@Override
	public void backward() {
		if(face == Dir.S &&
				row+1 < env.getHeight() &&
				!env.getCellContent(col, row+1) &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO)){
			row++;
		}
		
		if(face == Dir.W &&
				col+1 < env.getWidth() &&
				!env.getCellContent(col+1, row) &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO)){
			col++;
		}
		
		if(face == Dir.N &&
				row-1 >= 0 &&
				!env.getCellContent(col, row-1) &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO)){
			row--;
		}
		
		if(face == Dir.E &&
				col-1 >= 0 &&
				!env.getCellContent(col-1, row) &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO)){
			col--;
		}
	}

	@Override
	public void turnL() {
		if(face == Dir.N) face = Dir.W;
		if(face == Dir.W) face = Dir.S;
		if(face == Dir.S) face = Dir.E;
		if(face == Dir.E) face = Dir.N;
	}

	@Override
	public void turnR() {
		if(face == Dir.N) face = Dir.E;
		if(face == Dir.W) face = Dir.N;
		if(face == Dir.S) face = Dir.W;
		if(face == Dir.E) face = Dir.S;
	}

	@Override
	public void StrafeL() {
		if(face == Dir.N &&
				col+1 < env.getWidth() &&
				!env.getCellContent(col+1, row) &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO)){
			col++;
		}
		
		if(face == Dir.E &&
				row+1 < env.getHeight() &&
				!env.getCellContent(col, row+1) &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO)){
			row++;
		}
		
		if(face == Dir.S &&
				col-1 >= 0 &&
				!env.getCellContent(col-1, row) &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO)){
			col--;
		}
		
		if(face == Dir.W &&
				row-1 >= 0 &&
				!env.getCellContent(col, row-1) &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO)){
			row--;
		}
	}

	@Override
	public void StrafeR() {
		if(face == Dir.S &&
				col+1 < env.getWidth() &&
				!env.getCellContent(col+1, row) &&
				(env.getCellNature(col+1, row) == Cell.EMP || env.getCellNature(col+1, row) == Cell.DWO)){
			col++;
		}
		
		if(face == Dir.W &&
				row+1 < env.getHeight() &&
				!env.getCellContent(col, row+1) &&
				(env.getCellNature(col, row+1) == Cell.EMP || env.getCellNature(col, row+1) == Cell.DNO)){
			row++;
		}
		
		if(face == Dir.N &&
				col-1 >= 0 &&
				!env.getCellContent(col-1, row) &&
				(env.getCellNature(col-1, row) == Cell.EMP || env.getCellNature(col-1, row) == Cell.DWO)){
			col--;
		}
		
		if(face == Dir.E &&
				row-1 >= 0 &&
				!env.getCellContent(col, row-1) &&
				(env.getCellNature(col, row-1) == Cell.EMP || env.getCellNature(col, row-1) == Cell.DNO)){
			row--;
		}
	}

}
