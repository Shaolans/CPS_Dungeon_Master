package dungeon.master.mbt.test;

import org.junit.After;
import org.junit.Before;

import dungeon.master.components.Player;
import dungeon.master.contracts.PlayerServiceContract;
import dungeon.master.enumerations.Dir;
import dungeon.master.services.PlayerService;

public class PlayerServiceTest extends MobServiceTest {
	private PlayerService player;
	
	@Before
	public void beforeTests(){
		super.beforeTests();
		player = new PlayerServiceContract(new Player());
		mob = player;
		player.init(env, 5, 7, Dir.N, 10);
	}
	
	@After
	public void afterTests(){
		super.afterTests();
		player = null;
	}
	
	
	
}
