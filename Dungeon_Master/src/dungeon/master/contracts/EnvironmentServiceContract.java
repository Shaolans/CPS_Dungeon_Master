package dungeon.master.contracts;


import java.util.ArrayList;
import java.util.List;

import dungeon.master.decorators.EnvironmentServiceDecorator;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Option;
import dungeon.master.enumerations.Pair;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class EnvironmentServiceContract extends EnvironmentServiceDecorator implements EnvironmentService {

	public EnvironmentServiceContract(EnvironmentService env) {
		super(env);
	}

	public void checkInvariant() {
		//NONE

		/* isReachable */
		Pair<Integer, Integer> in = null, out = null;
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				if(getCellNature(i,j) == Cell.IN){
					in = new Pair<Integer, Integer>(i,j);
				}
				if(getCellNature(i,j) == Cell.OUT){
					out = new Pair<Integer, Integer>(i,j);
				}
			}
		}

		if(!(isReachable(in.getValue1(), in.getValue2(), out.getValue1(), out.getValue2()) ==
				existReachable(in.getValue1(), in.getValue2(), out.getValue1(), out.getValue2()))){
			throw new InvariantError("isReachable(x1,y1,x2,y2) = exists P in Array[int,int]... does not hold");
		}


		/* isReady */
		int xi = in.getValue1();
		int yi = in.getValue2();

		int xo = out.getValue1();
		int yo = out.getValue2();

		boolean res = (getCellNature(xi, yi) == Cell.IN && getCellNature(xo, yo) == Cell.OUT);
		res = res & isReachable(xi, yi, xo, yo);

		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				if(i != xi || j != yi){
					if(getCellNature(i, j) == Cell.IN){
						res = res & false;
					}
				}
				if(i != xo || j != yo){
					if(getCellNature(i, j) == Cell.OUT){
						res = res & false;
					}
				}
			}
		}

		boolean treasure = false;
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				if(getCellNature(i,j) == Cell.TRS) {
					treasure = true;
				}
			}
		}

		res = res && treasure;
		if(!(isReady() == res)){
			throw new InvariantError("isReady() = exists xi,yi,xo,yo in intxintxintxint ... does not hold");
		}
	}

	@Override
	public void closeDoor(int x, int y) {
		/* preconditions */
		if(getCellContent(x, y).getValue() != null) {
			throw new PreconditionError("getCellContent(x,y) == No does not hold");
		}

		if( !(getCellNature(x, y)==Cell.DNO || getCellNature(x,y)==Cell.DWO )){
			throw new PreconditionError("getCellNature(x,y) in {DNO, DWO} does not hold");
		}

		/*invariants*/
		checkInvariant();

		/*capture */

		Cell[][] mapGrille_atPre = new Cell[getWidth()][getHeight()] ;

		for(int i=0; i<getWidth(); i++){
			for(int j=0; j<getHeight(); j++){
				mapGrille_atPre[i][j] = getCellNature(i,j);
			}
		}

		super.closeDoor(x,y);

		/*invariants*/
		checkInvariant();

		/*postconditions */

		if(mapGrille_atPre[x][y]==Cell.DWO && getCellNature(x,y)!=Cell.DWC){
			throw new PostconditionError("getCellNature(x,y)@pre=DWO \\impl getCellNature(x,y)=DWC does not hold");
		}

		if(mapGrille_atPre[x][y]==Cell.DNO && getCellNature(x,y)!=Cell.DNC){
			throw new PostconditionError("getCellNature(x,y)@pre=DNO \\impl getCellNature(x,y)=DNC does not hold");
		}

		for(int i=0; i<getWidth(); i++){
			for(int j=0; j<getHeight(); j++){

				if(i!=x && j!=y){
					if(mapGrille_atPre[i][j]!=getCellNature(i,j)){
						throw new PostconditionError("\\forall u in [0, getWidth()-1] \\forall v in [0, getHeight()-1] \\with (u!=x or v!=y) 	\\impl getCellNature(x,y) = getCellNature(x,y)@pre	");
					}
				}
			}
		}

	}

	@Override
	public Option<EntityService> getCellContent(int x, int y) {

		return ((EnvironmentService)map).getCellContent(x, y);

	}

	@Override
	public int getHeight() {

		return super.getHeight();
	}

	@Override
	public int getWidth() {

		return super.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		/* Preconditions */
		if(!(0 <= x && x <= getWidth() && 0 <= y && y <= getHeight())) {
			throw new PreconditionError("0<=x<getWidth() and 0<=y<getHeigth() does not hold");
		}

		return super.getCellNature(x, y);
	}



	@Override
	public void init(int w, int h) {

		/*preconditions*/

		if( !(w>0 && h>0)){
			throw new PreconditionError("w>0 && h>0 does not hold");
		}

		super.init(w, h);

		/*invariants*/
		checkInvariant();

		/*postconditions*/
		if(!(getHeight()==h && getWidth()==w)){
			throw new PostconditionError("getHeigth()==h and getWidth()==w does not hold");
		}

	}

	@Override
	public void openDoor(int x, int y) {

		/* preconditions */

		if( !(getCellNature(x, y)==Cell.DNC || getCellNature(x,y)==Cell.DWC )){
			throw new PreconditionError("getCellNature(x,y) in {DNC, DWC} does not hold");
		}

		/*invariants*/
		checkInvariant();

		/*capture */

		Cell[][] mapGrille_atPre = new Cell[getWidth()][getHeight()] ;

		for(int i=0; i<getWidth(); i++){
			for(int j=0; j<getHeight(); j++){
				mapGrille_atPre[i][j] = getCellNature(i,j);
			}
		}

		super.openDoor(x,y);

		/*invariants*/
		checkInvariant();

		/*postconditions */

		if(mapGrille_atPre[x][y]==Cell.DNC && getCellNature(x,y)!=Cell.DNO){
			throw new PostconditionError("getCellNature(x,y)@pre=DWC \\impl getCellNature(x,y)=DWO does not hold");
		}

		if(mapGrille_atPre[x][y]==Cell.DWC && getCellNature(x,y)!=Cell.DWO){
			throw new PostconditionError("getCellNature(x,y)@pre=DNC \\impl getCellNature(x,y)=DNO does not hold");
		}

		for(int i=0; i<getWidth(); i++){
			for(int j=0; j<getHeight(); j++){

				if(i!=x && j!=y){
					if(mapGrille_atPre[i][j]!=getCellNature(i,j)){
						throw new PostconditionError("\\forall u in [0, getWidth()-1] \\forall v in [0, getHeight()-1] \\with (u!=x or v!=y) 	\\impl getCellNature(x,y) = getCellNature(x,y)@pre	");
					}
				}
			}
		}

	}

	@Override
	public void setCellContent(int col, int row, EntityService mob) {
		/* Preconditions */
		if(!(0 <= col && col < getWidth() && 0 <= row && row < getHeight())) {
			System.out.println(col + " "+getHeight());
			System.out.println(row+" "+getWidth());
			throw new PreconditionError("0 <= col < getHeight() and 0 <= row < getWidth() does not hold");
		}

		/* Invariants */
		checkInvariant();

		/* Capture */
		@SuppressWarnings("unchecked")
		Option<EntityService> map[][] = new Option[getWidth()][getHeight()];
		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				map[i][j] = getCellContent(i, j);
			}
		}

		/* Run */
		super.setCellContent(col, row, mob);

		/* Invariants */
		checkInvariant();

		/* Postconditions */
		if(!(getCellContent(col, row).getValue() == mob)) {
			throw new PostconditionError("getCellContent(col, row) == Option<Mob>(mob) does not hold");
		}

		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				if(i != col || j != row) {
					if(!(getCellContent(i, j) == map[i][j])) {
						throw new PostconditionError("\\forall x \\in [0;getHeight()-1], \\forall y \\in [0;getWidth()-] and x!=col and y!=row,"
								+ " getCellContent(x,y) = getCellContent(x,y)@pre does not hold");
					}
				}
			}
		}

	}

	public boolean existReachable(int x1, int y1, int x2, int y2){
		Cell [][]tab = new Cell[getWidth()][getHeight()];
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				tab[i][j] = getCellNature(i, j);
			}
		}
		List<Pair<Integer, Integer>> track = new ArrayList<>();
		if(!backtracking(x1, y1, x2, y2, track, tab)) return false;
		if(!(track.get(0).getValue1() == x1 && track.get(0).getValue2() == y1)) return false;
		if(!(track.get(track.size()-1).getValue1() == x2 && track.get(track.size()-1).getValue2() == y2)) return false;

		for(int i = 1; i < track.size(); i++){
			Pair<Integer, Integer> uv = track.get(i-1);
			Pair<Integer, Integer> st = track.get(i);
			if((uv.getValue1().intValue()-st.getValue1().intValue())*(uv.getValue1().intValue()-st.getValue1().intValue())+
					(uv.getValue2().intValue()-st.getValue2().intValue())*(uv.getValue2().intValue()-st.getValue2().intValue()) != 1){
				return false;
			}
		}

		for(int i = 1; i < track.size()-1; i++){
			if(getCellNature(track.get(i).getValue1(), track.get(i).getValue2()) == Cell.WLL) return false;
		}

		return true;

	}

	public boolean backtracking(int x, int y, int xend, int yend, List<Pair<Integer, Integer>> track, Cell[][] tab){
		boolean res;

		if(x == xend && y == yend && tab[x][y] != Cell.WLL){
			track.add(0, new Pair<Integer, Integer>(x,y));
			return true;
		}

		tab[x][y] = Cell.WLL;

		if(x+1 < tab.length && tab[x+1][y] != Cell.WLL){
			res = backtracking(x+1, y, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}

		if(x-1 > 0 && tab[x-1][y] != Cell.WLL){
			res = backtracking(x-1, y, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}

		if(y+1 < tab[0].length && tab[x][y+1] != Cell.WLL){
			res = backtracking(x, y+1, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}

		if(y-1 > 0 && tab[x][y-1] != Cell.WLL){
			res = backtracking(x, y-1, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args){
		Cell tab[][] = {
				{Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL},
				{Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL, Cell.WLL},
				{Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL},
				{Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP},
				{Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP}
		};

		EditMapServiceContract emsc = new EditMapServiceContract(null);
		List<Pair<Integer,Integer>> list = new ArrayList<>();
		System.out.println(emsc.backtracking(0, 0, 4, 4, list, tab));
		for(Pair<Integer, Integer> p: list){
			System.out.println(p.getValue1()+" "+p.getValue2());
		}
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		/* Preconditions */
		if(!(getCellNature(x1,y1) != Cell.WLL && getCellNature(x2,y2) != Cell.WLL)){
			throw new PreconditionError("getCellNature(x1,y1) != WLL and getCellNature(x2,y2) != WLL does not hold");
		}

		return getDelegate().isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		return getDelegate().isReady();
	}

	@Override
	public void setNature(int col, int row, Cell cell) {
		/* Preconditions */
		if(!(0 <= col && col < getWidth() && 0 <= row && row < getHeight())){
			throw new PreconditionError("0<= col < getWidth() and 0<= row < getHeight() does not hold");
		}

		/* Invariant */
		checkInvariant();

		/* Capture */
		Cell[][] mapGrille_atPre = new Cell[getWidth()][getHeight()] ;

		for(int i=0; i<getWidth(); i++){
			for(int j=0; j<getHeight(); j++){
				mapGrille_atPre[i][j] = getCellNature(i,j);
			}
		}


		/* Run */
		getDelegate().setNature(col, row, cell);


		/* Invariant */
		checkInvariant();

		/* Postconditions */

		if(!(getCellNature(col, row) == cell)){
			throw new PostconditionError("getCellNature(col,row) == cell does not hold");
		}

		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				if(col != i || row != j){
					if(!(mapGrille_atPre[i][j] == getCellNature(i, j))){
						throw new PostconditionError("\\forall u,v in intxint, u !=x or v !=y \\implies getCellNature(u,v) == getCellNature(u,v)@pre does not hold");
					}
				}
			}
		}
	}

}
