package dungeon.master.contracts;


import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Option;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class EnvironmentServiceContract extends MapServiceContract implements EnvironmentService {

	public EnvironmentServiceContract(EnvironmentService env) {
		super(env);
	}

	
	@Override
	public void closeDoor(int x, int y) {
		/* preconditions */
		if(getCellContent(x, y) != null) {
			throw new PreconditionError("getCellContent(x,y) == No does not hold");
		}
		
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
	
	@Override
	public Option<MobService> getCellContent(int x, int y) {
		
		return ((EnvironmentService)map).getCellContent(x, y);

	}

}
