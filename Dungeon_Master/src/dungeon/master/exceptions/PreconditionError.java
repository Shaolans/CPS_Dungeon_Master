package dungeon.master.exceptions;

public class PreconditionError extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PreconditionError(){
		super("PreconditionError");
	}
	
	public PreconditionError(String msg){
		super("PreconditionError : "+msg);
	}

}
