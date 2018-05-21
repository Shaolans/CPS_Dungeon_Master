package dungeon.master.decorators;

import dungeon.master.services.MonsterService;

public class MonsterServiceDecorator extends EntityServiceDecorator implements MonsterService {
	public MonsterServiceDecorator(MonsterService delegate) {
		super(delegate);
	}
	
	public MonsterService getDelegate() {
		return (MonsterService)super.getDelegate();
	}
}
