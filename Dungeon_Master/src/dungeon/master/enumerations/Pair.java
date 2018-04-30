package dungeon.master.enumerations;

public class Pair<T,U> {
	private T value1;
	private U value2;
	
	public Pair(T t, U u){
		value1 = t;
		value2 = u;
	}
	
	public T getValue1(){
		return value1;
	}
	
	public U getValue2(){
		return value2;
	}
}
