package dungeon.master.exceptions;

public class PostconditionError extends Error{
	
private static final long serialVersionUID = 1L;
	
	public PostconditionError(){
		super("PostconditionError");
	}
	
	public PostconditionError(String msg){
		super("PostconditionError : "+msg);
}
	
}
