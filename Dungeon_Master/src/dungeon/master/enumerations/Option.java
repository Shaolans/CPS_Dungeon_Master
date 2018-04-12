package dungeon.master.enumerations;

public class Option<T> {
	private T value;
	
	public Option(T value) {
		this.value = value;
	}
	
	public Option() {
		value = null;
	}
	
	public T getValue() {
		return value;
	}
	
}
