package dungeon.master.decorators;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;
import dungeon.master.enumerations.Option;
import dungeon.master.services.EntityService;
import dungeon.master.services.EnvironmentService;
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
	public Option<EntityService> getContent(int col, int row) {
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

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir) {
		getDelegate().init(env, col, row, dir);
	}

	@Override
	public void init(EnvironmentService env, int col, int row, Dir dir, int hp, int damage){
		getDelegate().init(env, col, row, dir, hp, damage);
	}

	@Override
	public void setLastCom(Command cmd) {
		getDelegate().setLastCom(cmd);
	}

	@Override
	public void openDoor() {
		getDelegate().openDoor();
		
	}

	@Override
	public void closeDoor() {
		getDelegate().closeDoor();
	}

}
