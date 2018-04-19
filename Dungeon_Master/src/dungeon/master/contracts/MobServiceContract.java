package dungeon.master.contracts;

import dungeon.master.decorators.MobServiceDecorator;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class MobServiceContract extends MobServiceDecorator implements MobService {

	public MobServiceContract(MobService decorator) {
		super(decorator);
	}
	
	public MobService getDelegate(){
		return super.getDelegate();
	}
	
	public void checkInvariant() {
		if(!(0 <= getCol() && getEnv().getWidth() < getCol())){
			throw new InvariantError("0 <= getCol() <= getEnv().getWidth() does not hold");
		}
		
		if(!(0 <= getRow() && getEnv().getHeight() < getRow())){
			throw new InvariantError("0 <= getRow() <= getEnv().getHeight() does not hold");
		}
		
		if(!(getEnv().getCellNature(getCol(), getRow()) != Cell.WLL ||
				getEnv().getCellNature(getCol(), getRow()) != Cell.DNC ||
				getEnv().getCellNature(getCol(), getRow()) != Cell.DWC)) {
			throw new InvariantError("getEnv().getCellNature(getRow(),getCol()) \\not \\in {WLL, DNC, DWC} does not hold");
		}
	}
	

	@Override
	public void init(EnvironmentService env, int row, int col, Dir dir) {
		/* Preconditions */
		if(!(0 <= row && env.getHeight() < row)) throw new PreconditionError("0 <= row <= env.getHeight() does not hold");
		if(!(0 <= col && env.getWidth() < col)) throw new PreconditionError("0 <= col <= env.getWidth() does not hold");
		
		super.init(env, row, col, dir);
		
		/* Invariants */
		checkInvariant();
		
		/* Postconditions */
		if(!(getEnv()==env)) throw new PostconditionError("getEnv() = env does not hold");
		if(!(getCol()==col)) throw new PostconditionError("getCol() = col does not hold");
		if(!(getRow()==row)) throw new PostconditionError("getRow() = row does not hold");
		if(!(getFace()==dir)) throw new PostconditionError("getFace() = dir does not hold");
		
	}

	@Override
	public void forward() {
		/* Preconditions */
		//NONE
		
		checkInvariant();
		
		/* Capture */
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		super.forward();
		
		checkInvariant();
		
		/* Postconditions */
		if(getFace() == Dir.N) {
			if((getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.DWO) &&
					row_atpre+1 < getEnv().getHeight() &&
					getEnv().getCellContent(col_atpre, row_atpre+1) == null) {
				if(!(getRow()==row_atpre+1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == N ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == N ... does not hold");
				}
			}
		}
		
		
		if(getFace() == Dir.E) {
			if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
					col_atpre+1 < getEnv().getWidth() &&
					getEnv().getCellContent(col_atpre+1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre+1)) {
					throw new PostconditionError("getFace()@pre == E ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == E ... does not hold");
				}
			}
		}
		
		if(getFace() == Dir.S) {
			if((getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.DWO) &&
					row_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre, row_atpre-1) == null) {
				if(!(getRow()==row_atpre-1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == S ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == S ... does not hold");
				}
			}
		}
		
		if(getFace() == Dir.W) {
			if((getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.DNO) &&
					col_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre-1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre-1)) {
					throw new PostconditionError("getFace()@pre == W ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == W ... does not hold");
				}
			}
		}
		
	}

	@Override
	public void backward() {
		/* Preconditions */
		//NONE
		
		checkInvariant();
		
		/* Capture */
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		super.backward();
		
		checkInvariant();
		
		/* Postconditions */
		if(getFace() == Dir.S) {
			if((getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.DWO) &&
					row_atpre+1 < getEnv().getHeight() &&
					getEnv().getCellContent(col_atpre, row_atpre+1) == null) {
				if(!(getRow()==row_atpre+1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == N ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == N ... does not hold");
				}
			}
		}
		
		
		if(getFace() == Dir.W) {
			if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
					col_atpre+1 < getEnv().getWidth() &&
					getEnv().getCellContent(col_atpre+1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre+1)) {
					throw new PostconditionError("getFace()@pre == E ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == E ... does not hold");
				}
			}
		}
		
		if(getFace() == Dir.N) {
			if((getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.DWO) &&
					row_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre, row_atpre-1) == null) {
				if(!(getRow()==row_atpre-1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == S ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == S ... does not hold");
				}
			}
		}
		
		if(getFace() == Dir.E) {
			if((getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.DNO) &&
					col_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre-1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre-1)) {
					throw new PostconditionError("getFace()@pre == W ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == W ... does not hold");
				}
			}
		}
		
	}

	@Override
	public void turnL() {
		/* Preconditions */
		//NONE
		
		checkInvariant();
		
	
		/* Capture */
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();

		super.turnL();
		
		checkInvariant();
		
		/* Postconditions */
		if(face_atpre==Dir.N) {
			if(!(getFace()==Dir.W)) {
				throw new PostconditionError("getFace()@pre==N \\implies getFace()==W does not hold");
			}
		}
		
		if(face_atpre==Dir.W) {
			if(!(getFace()==Dir.S)) {
				throw new PostconditionError("getFace()@pre==W \\implies getFace()==S does not hold");
			}
		}
		
		if(face_atpre==Dir.S) {
			if(!(getFace()==Dir.E)) {
				throw new PostconditionError("getFace()@pre==S \\implies getFace()==E does not hold");
			}
		}
		
		if(face_atpre==Dir.E) {
			if(!(getFace()==Dir.N)) {
				throw new PostconditionError("getFace()@pre==E \\implies getFace()==N does not hold");
			}
		}
		
		if(!(row_atpre==getRow() && col_atpre==getCol())) {
			throw new PostconditionError("getRow()==getRow@pre and getCol()==getCol()@pre does not hold");
		}
	}

	@Override
	public void turnR() {
		/* Preconditions */
		//NONE
		
		checkInvariant();
		
		/* Capture */
		Dir face_atpre = getFace();
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		super.turnR();
		
		checkInvariant();
		
		/* Postconditions */
		if(face_atpre==Dir.N) {
			if(!(getFace()==Dir.E)) {
				throw new PostconditionError("getFace()@pre==N \\implies getFace()==E does not hold");
			}
		}
		
		if(face_atpre==Dir.W) {
			if(!(getFace()==Dir.N)) {
				throw new PostconditionError("getFace()@pre==W \\implies getFace()==N does not hold");
			}
		}
		
		if(face_atpre==Dir.S) {
			if(!(getFace()==Dir.W)) {
				throw new PostconditionError("getFace()@pre==S \\implies getFace()==W does not hold");
			}
		}
		
		if(face_atpre==Dir.E) {
			if(!(getFace()==Dir.S)) {
				throw new PostconditionError("getFace()@pre==E \\implies getFace()==S does not hold");
			}
		}
		
		if(!(row_atpre==getRow() && col_atpre==getCol())) {
			throw new PostconditionError("getRow()==getRow@pre and getCol()==getCol()@pre does not hold");
		}
		
	}

	@Override
	public void StrafeL() {
		/* Preconditions */
		//NONE
		
		/* Invariants */
		checkInvariant();
		
		/* Capture */
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		/* run */
		super.turnR();
		
		checkInvariant();
		
		/* Postconditions */
		if(getFace() == Dir.N) {
			if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
					col_atpre+1 < getEnv().getWidth() &&
					getEnv().getCellContent(col_atpre+1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre+1)) {
					throw new PostconditionError("getFace()@pre == N ... (StrafeL) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == N ... (StrafeL) does not hold");
				}
			}
		}
		
		
		if(getFace() == Dir.E) {
			if((getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.DWO) &&
					row_atpre+1 < getEnv().getHeight() &&
					getEnv().getCellContent(col_atpre, row_atpre+1) == null) {
				if(!(getRow()==row_atpre+1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == E ... (StrafeL) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == E ... (StrafeL) does not hold");
				}
			}
		}
		
		if(getFace() == Dir.W) {
			if((getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.DWO) &&
					row_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre, row_atpre-1) == null) {
				if(!(getRow()==row_atpre-1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == W ... (StrafeL) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == W ... (StrafeL) does not hold");
				}
			}
		}
		
		if(getFace() == Dir.S) {
			if((getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.DNO) &&
					col_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre-1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre-1)) {
					throw new PostconditionError("getFace()@pre == S ... (StrafeL) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == S ... (StrafeL) does not hold");
				}
			}
		}
	}

	@Override
	public void StrafeR() {
		/* Preconditions */
		//NONE
		
		/* Invariants */
		checkInvariant();
		
		/* Capture */
		int row_atpre = getRow();
		int col_atpre = getCol();
		
		/* run */
		super.turnL();
		
		checkInvariant();
		
		/* Postconditions */
		if(getFace() == Dir.S) {
			if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
					col_atpre+1 < getEnv().getWidth() &&
					getEnv().getCellContent(col_atpre+1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre+1)) {
					throw new PostconditionError("getFace()@pre == N ... (StrafeR) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == N ... (StrafeR) does not hold");
				}
			}
		}
		
		
		if(getFace() == Dir.W) {
			if((getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.DWO) &&
					row_atpre+1 < getEnv().getHeight() &&
					getEnv().getCellContent(col_atpre, row_atpre+1) == null) {
				if(!(getRow()==row_atpre+1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == E ... (StrafeR) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == E ... (StrafeR) does not hold");
				}
			}
		}
		
		if(getFace() == Dir.E) {
			if((getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.DWO) &&
					row_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre, row_atpre-1) == null) {
				if(!(getRow()==row_atpre-1 && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == W ... (StrafeR) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == W ... (StrafeR) does not hold");
				}
			}
		}
		
		if(getFace() == Dir.N) {
			if((getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre-1, row_atpre) == Cell.DNO) &&
					col_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre-1, row_atpre) == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre-1)) {
					throw new PostconditionError("getFace()@pre == S ... (StrafeR) does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == S ... (StrafeR) does not hold");
				}
			}
		}
		
	}
	

}
