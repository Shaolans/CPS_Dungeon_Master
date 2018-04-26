package dungeon.master.decorators;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Option;
import dungeon.master.services.MobService;
import dungeon.master.services.PlayerService;

public class PlayerServiceDecorator extends EntityServiceDecorator implements PlayerService {
	
	public PlayerServiceDecorator(PlayerService ps) {
		super(ps);
	}
	
	public PlayerService getDelegate() {
		return (PlayerService)super.getDelegate();
	}

	@Override
	public Option<Command> getLastCom() {
		return getDelegate().getLastCom();
	}

	@Override
	public Option<MobService> getContent(int col, int row) {
		return getDelegate().getContent(col, row);
	}

	@Override
	public Cell getNature(int col, int row) {
		return getDelegate().getNature(col, row);
	}

	@Override
	public boolean isViewable(int col, int row) {
		return getDelegate().isViewable(col, row);
	}

}
