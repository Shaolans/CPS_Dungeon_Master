package dungeon.master.contracts;


import dungeon.master.decorators.EnvironmentServiceDecorator;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Option;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class EnvironmentServiceContract extends EnvironmentServiceDecorator implements EnvironmentService {

	public EnvironmentServiceContract(EnvironmentService env) {
		super(env);
	}

	public void checkInvariant() {
		//NONE
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
		
		if(mapGrille_atPre[x][y]==Cell.DNO && getCellNature(x,y)!=Cell.DNC){
			throw new PostconditionError("getCellNature(x,y)@pre=DWO \\impl getCellNature(x,y)=DWC does not hold");
		}
		
		if(mapGrille_atPre[x][y]==Cell.DWO && getCellNature(x,y)!=Cell.DWC){
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
	public Option<MobService> getCellContent(int x, int y) {
		
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
	public void setCellContent(int col, int row, MobService mob) {
		/* Preconditions */
		if(!(0 <= col && col < getHeight() && 0 <= row && row < getWidth())) {
			throw new PreconditionError("0 <= col < getHeight() and 0 <= row < getWidth() does not hold");
		}
		
		/* Invariants */
		checkInvariant();
		
		/* Capture */
		@SuppressWarnings("unchecked")
		Option<MobService> map[][] = new Option[getWidth()][getHeight()];
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
	
}
