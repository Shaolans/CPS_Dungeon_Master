package dungeon.master.exceptions;

public class InvariantError extends Error {

private static final long serialVersionUID = 1L;
	
	
	public InvariantError(){
		super("InvariantError");
	}
	
	public InvariantError(String msg){
		super("InvariantError : "+msg);
}
	
}
