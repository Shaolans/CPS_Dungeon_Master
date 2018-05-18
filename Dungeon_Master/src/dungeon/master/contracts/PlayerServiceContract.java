package dungeon.master.contracts;

import java.util.Random;

import dungeon.master.decorators.PlayerServiceDecorator;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;
import dungeon.master.enumerations.Option;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;
import dungeon.master.services.PlayerService;

public class PlayerServiceContract extends PlayerServiceDecorator implements PlayerService {

	public PlayerServiceContract(PlayerService ps) {
		super(ps);
	}

	public PlayerService getDelegate() {
		return super.getDelegate();
	}

	@Override
	public void setLastCom(Command cmd) {
		/* Precondition */

		/* Invariants */
		checkInvariant();

		/* Capture */

		/* Run */
		getDelegate().setLastCom(cmd);

		/* Invariants */
		checkInvariant();

		/* Postcondition */
		if(!(getLastCom().getValue() == cmd)) {
			throw new PostconditionError("getLastCom().getValue() == cmd does not hold");
		}


	}


	public void checkInvariant(){
		if(!(0 <= getCol() && getCol() < getEnv().getWidth())){
			throw new InvariantError("0 <= getCol() <= getEnv().getWidth() does not hold");
		}

		if(!(0 <= getRow() && getRow() < getEnv().getHeight())){
			throw new InvariantError("0 <= getRow() <= getEnv().getHeight() does not hold");
		}

		if(!(getEnv().getCellNature(getCol(), getRow()) != Cell.WLL ||
				getEnv().getCellNature(getCol(), getRow()) != Cell.DNC ||
				getEnv().getCellNature(getCol(), getRow()) != Cell.DWC)) {
			throw new InvariantError("getEnv().getCellNature(getRow(),getCol()) \\not \\in {WLL, DNC, DWC} does not hold");
		}



		for(int i=0; i<4; i++){

			int u=0,v=0;

			Random r = new Random();
			u = r.nextInt(3)-1;
			v = r.nextInt(3)-1;

			Dir dir = getFace();
			Option<MobService> content = getContent(u, v);
			Cell nature = getNature(u,v);

			if( dir==Dir.N && !(content.getValue() == getEnv().getCellContent(getCol()+u, getRow()+v).getValue())){
				throw new InvariantError("\\inv getFace()==N \\implies getContent(u,v) = getEnv().getCellContent(getCol()+u,getRow()+v) does not hold");
			}

			if( dir==Dir.N && !(nature == getEnv().getCellNature(getCol()+u, getRow()+v))){
				throw new InvariantError("\\inv getFace()==N \\implies getNature(u,v) = getEnv().getCellNature(getCol()+u,getRow()+v) does not hold");
			}

			if( dir==Dir.S && !(content.getValue() == getEnv().getCellContent(getCol()-u, getRow()-v).getValue())){
				throw new InvariantError("\\inv getFace()==S \\implies getContent(u,v) = getEnv().getCellContent(getCol()-u,getRow()-v) does not hold");
			}

			if( dir==Dir.S && !(nature == getEnv().getCellNature(getCol()-u, getRow()-v))){
				throw new InvariantError("\\inv getFace()==S \\implies getNature(u,v) = getEnv().getCellNature(getCol()-u,getRow()-v) does not hold");
			}

			if( dir==Dir.E && !(content.getValue() == getEnv().getCellContent(getCol()+v, getRow()-u).getValue())){
				throw new InvariantError("\\inv getFace()==E \\implies getContent(u,v) = getEnv().getCellContent(getCol()+u,getRow()-v) does not hold");
			}

			if( dir==Dir.E && !(nature == getEnv().getCellNature(getCol()+v, getRow()-u))){
				throw new InvariantError("\\inv getFace()==E \\implies getNature(u,v) = getEnv().getCellNature(getCol()+u,getRow()-v) does not hold");
			}

			if( dir==Dir.W && !(content.getValue() == getEnv().getCellContent(getCol()-v, getRow()+u).getValue())){
				throw new InvariantError("\\inv getFace()==W \\implies getContent(u,v) = getEnv().getCellContent(getCol()-u,getRow()+v) does not hold");
			}

			if( dir==Dir.W && !(nature == getEnv().getCellNature(getCol()-v, getRow()+u))){
				throw new InvariantError("\\inv getFace()==W \\implies getNature(u,v) = getEnv().getCellNature(getCol()-u,getRow()+v) does not hold");
			}


		}

		for(int u = -1; u< 1; u++){
			for(int v = -1; v<1; v++){
				if( !isViewable(u, v) ){
					throw new InvariantError("\\inv \\forall u,v \\in [-1,1]x[-1,1], !isViewable(u,v) does not hold");
				}
			}
		}

		if( !(isViewable(-1, 2) == (getNature(-1, 1) != Cell.WLL && getNature(-1, 1) != Cell.DWC && getNature(-1, 1) != Cell.DNC ) ) ){
			throw new InvariantError("\\inv isViewable(-1,2) = getNature(-1,1) \not \\in {WALL, DWC, DNC} does not hold");
		}

		if( !(isViewable(0, 2) == (getNature(0, 1)!= Cell.WLL && getNature(0, 1)!= Cell.DWC && getNature(0, 1) != Cell.DNC ) ) ){
			throw new InvariantError("\\inv isViewable(0,2) = getNature(0,1) \not \\in {WALL, DWC, DNC} does not hold");
		}

		if( !(isViewable(1, 2) == (getNature(1, 1)!= Cell.WLL && getNature(1, 1)!= Cell.DWC && getNature(1, 1) != Cell.DNC ) ) ){
			throw new InvariantError("\\inv isViewable(1,2) = getNature(1,1) \not \\in {WALL, DWC, DNC} does not hold");
		}

		if( !(isViewable(-1, 3) == (getNature(-1, 2)!= Cell.WLL && getNature(-1, 2)!= Cell.DWC && getNature(-1, 2) != Cell.DNC ) && isViewable(-1, 2) ) ){
			throw new InvariantError("\\inv isViewable(-1,3) = getNature(-1,2) \\not \\in {WALL, DWC, DNC} and isViewable(-1,2) does not hold");
		}

		if( !(isViewable(0, 3) == (getNature(0, 2)!= Cell.WLL && getNature(0, 2)!= Cell.DWC && getNature(0, 2) != Cell.DNC ) && isViewable(0, 2) ) ){
			throw new InvariantError("\\inv isViewable(0,3) = getNature(0,2) \\not \\in {WALL, DWC, DNC} and isViewable(0,2) does not hold");
		}

		if( !(isViewable(1, 3) == (getNature(1, 2)!= Cell.WLL && getNature(1, 2)!= Cell.DWC && getNature(1, 2) != Cell.DNC ) && isViewable(1, 2) ) ){
			throw new InvariantError("\\inv isViewable(1,3) = getNature(1,2) \\not \\in {WALL, DWC, DNC} and isViewable(1,2) does not hold");
		}


	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp) {
		/* preconditions */
		if(!(hp > 0)){
			throw new PreconditionError("h > 0 does not hold");
		}

		if(!(0 <= row && row < env.getHeight())) throw new PreconditionError("0 <= row <= env.getHeight() does not hold");
		if(!(0 <= col && col < env.getWidth())) throw new PreconditionError("0 <= col <= env.getWidth() does not hold");

		getDelegate().init(env, col, row, dir, hp);


		/* Invariants */
		checkInvariant();

		/* Postconditions */
		if(!(getEnv()==env)) throw new PostconditionError("getEnv() = env does not hold");
		if(!(getCol()==col)) throw new PostconditionError("getCol() = col does not hold");
		if(!(getRow()==row)) throw new PostconditionError("getRow() = row does not hold");
		if(!(getFace()==dir)) throw new PostconditionError("getFace() = dir does not hold");
		if(!(getHp()==hp)) throw new PostconditionError("getHp() = hp does not hold");

	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir) {
		/* Preconditions */
		if(!(0 <= row && row < env.getHeight())) throw new PreconditionError("0 <= row <= env.getHeight() does not hold");
		if(!(0 <= col && col < env.getWidth())) throw new PreconditionError("0 <= col <= env.getWidth() does not hold");

		super.init(env, col, row, dir);

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
					getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre+1)) {
					throw new PostconditionError("getFace()@pre == W ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == W ... does not hold");
				}
			}
		}

		if(getFace() == Dir.S) {
			if((getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre-1) == Cell.DWO) &&
					row_atpre-1 >= 0 &&
					getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
				if(!(getRow()==row_atpre && getCol()==col_atpre-1)) {
					throw new PostconditionError("getFace()@pre == E ... does not hold");
				}
			}else {
				if(!(getRow()==row_atpre && getCol()==col_atpre)) {
					throw new PostconditionError("getFace()@pre == E ... does not hold");
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
					getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
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
					getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
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
	public void strafeL() {
		/* Preconditions */
		//NONE

		/* Invariants */
		checkInvariant();

		/* Capture */
		int row_atpre = getRow();
		int col_atpre = getCol();

		/* run */
		super.strafeL();

		checkInvariant();

		/* Postconditions */
		if(getFace() == Dir.N) {
			if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
					col_atpre+1 < getEnv().getWidth() &&
					getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
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
					getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
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
	public void strafeR() {
		/* Preconditions */
		//NONE

		/* Invariants */
		checkInvariant();

		/* Capture */
		int row_atpre = getRow();
		int col_atpre = getCol();

		/* run */
		super.strafeR();

		checkInvariant();

		/* Postconditions */
		if(getFace() == Dir.S) {
			if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
					col_atpre+1 < getEnv().getWidth() &&
					getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
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
					getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
					getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
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

	@Override
	public Option<MobService> getContent(int col, int row){
		/* Preconditions */
		if(!((col==-1 || col == 0 || col == 1) && (row == -1 || row == 0 || row == 1 || row == 2 || row == 3))){
			throw new PreconditionError("col \\in {-1,0,1} and y \\in {-1,+3} does not hold");
		}

		return super.getContent(col, row);
	}

	@Override
	public Cell getNature(int col, int row) {
		/* Preconditions */
		if(!((col==-1 || col == 0 || col == 1) && (row == -1 || row == 0 || row == 1 || row == 2 || row == 3))){
			throw new PreconditionError("col \\in {-1,0,1} and y \\in {-1,+3} does not hold");
		}

		return super.getNature(col, row);
	}

	@Override
	public boolean isViewable(int col, int row) {
		/* Preconditions */
		if(!((col==-1 || col == 0 || col == 1) && (row == -1 || row == 0 || row == 1 || row == 2 || row == 3))){
			throw new PreconditionError("col \\in {-1,0,1} and y \\in {-1,+3} does not hold");
		}

		return super.isViewable(col, row);
	}

	@Override
	public void step() {
		/* Preconditions */
		//NONE

		/* Invariants */
		checkInvariant();

		/* Capture */
		Option<Command> lastcom_atpre = getLastCom();
		int row_atpre = getRow();
		int col_atpre = getCol();
		Dir face_atpre = getFace();

		/* Run */
		super.step();

		/* Invariants */
		checkInvariant();

		/* PostConditions */
		if(lastcom_atpre.getValue() == Command.FF) {
			if(getFace() == Dir.N) {
				if((getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.DWO) &&
						row_atpre+1 < getEnv().getHeight() &&
						getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
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
						getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
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


		if(lastcom_atpre.getValue() == Command.BB) {
			if(getFace() == Dir.S) {
				if((getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.EMP || getEnv().getCellNature(col_atpre, row_atpre+1) == Cell.DWO) &&
						row_atpre+1 < getEnv().getHeight() &&
						getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
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
						getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
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

		if(lastcom_atpre.getValue() == Command.LL) {
			if(getFace() == Dir.N) {
				if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
						col_atpre+1 < getEnv().getWidth() &&
						getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
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
						getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
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

		if(lastcom_atpre.getValue() == Command.RR) {
			if(getFace() == Dir.S) {
				if((getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.EMP || getEnv().getCellNature(col_atpre+1, row_atpre) == Cell.DNO) &&
						col_atpre+1 < getEnv().getWidth() &&
						getEnv().getCellContent(col_atpre+1, row_atpre).getValue() == null) {
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
						getEnv().getCellContent(col_atpre, row_atpre+1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre, row_atpre-1).getValue() == null) {
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
						getEnv().getCellContent(col_atpre-1, row_atpre).getValue() == null) {
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

		if(lastcom_atpre.getValue() == Command.TR) {
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

		if(lastcom_atpre.getValue() == Command.TL) {
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
	}





}
