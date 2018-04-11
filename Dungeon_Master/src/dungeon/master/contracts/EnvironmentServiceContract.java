package dungeon.master.contracts;


import dungeon.master.services.EnvironmentService;

public class EnvironmentServiceContract extends MapServiceContract implements EnvironmentService {

	public EnvironmentServiceContract(EnvironmentService env) {
		super(env);
	}

	
	//A VOIR OPTION[T]
	@Override
	public boolean getCellContent(int x, int y) {
		
		return ((EnvironmentService)map).getCellContent(x, y);

	}

}
