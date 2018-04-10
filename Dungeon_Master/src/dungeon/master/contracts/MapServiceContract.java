package dungeon.master.contracts;

import dungeon.master.decorators.MapServiceDecorator;
import dungeon.master.enumerations.Cell;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.MapService;

public class MapServiceContract extends MapServiceDecorator implements MapService {

	public MapServiceContract(MapService map) {
		super(map);
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
		
		return super.getCellNature(x, y);
	}
	
	
	public void checkInvariants(){
		// NONE
	}

	@Override
	public void init(int w, int h) {
		
		/*preconditions*/
		
		if( !(w>0 && h>0)){
			throw new PreconditionError("w>0 && h>0 does not hold");
		}
		
		/*invariants*/
		checkInvariants();
		
		super.init(w, h);
		
		/*invariants*/
		checkInvariants();
		
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
		checkInvariants();
		
		/*capture */
		
		Cell[][] mapGrille_atPre = new Cell[getWidth()][getHeight()] ;
		
		for(int i=0; i<getWidth(); i++){
			for(int j=0; j<getHeight(); j++){
				mapGrille_atPre[i][j] = getCellNature(i,j);
			}
		}
		
		super.openDoor(x,y);
		
		/*invariants*/
		checkInvariants();
		
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
	public void closeDoor(int x, int y) {
		/* preconditions */
		
		if( !(getCellNature(x, y)==Cell.DNO || getCellNature(x,y)==Cell.DWO )){
			throw new PreconditionError("getCellNature(x,y) in {DNO, DWO} does not hold");
		}
		
		/*invariants*/
		checkInvariants();
		
		/*capture */
		
		Cell[][] mapGrille_atPre = new Cell[getWidth()][getHeight()] ;
		
		for(int i=0; i<getWidth(); i++){
			for(int j=0; j<getHeight(); j++){
				mapGrille_atPre[i][j] = getCellNature(x,y);
			}
		}
		
		super.closeDoor(x,y);
		
		/*invariants*/
		checkInvariants();
		
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
	
	
	
}
