package dungeon.master.decorators;

import dungeon.master.services.CowService;

public class CowServiceDecorator extends EntityServiceDecorator implements CowService {
	public CowServiceDecorator(CowService delegate) {
		super(delegate);
	}
	
	public CowService getDelegate() {
		return (CowService)super.getDelegate();
	}

}
