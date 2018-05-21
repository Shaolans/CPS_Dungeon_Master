package dungeon.master.mbt.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Cow;
import dungeon.master.components.Engine;
import dungeon.master.components.Environment;
import dungeon.master.components.Player;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.contracts.EngineServiceContract;
import dungeon.master.contracts.EnvironmentServiceContract;
import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.CowService;
import dungeon.master.services.EngineService;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;
import dungeon.master.services.PlayerService;

public class EngineServiceTest {
	private EngineService es;
	private EnvironmentService env;
	
	@Before
	public void beforeTests() {
		es = new EngineServiceContract(new Engine());
		env = new EnvironmentServiceContract(new Environment());
		env.init(10, 15);
	}
	
	
	@After
	public void afterTests() {
		es = null;
		env = null;
	}
	
	@Test
	public void addEntityTransTest_1() {
		//cas positif
		try {
			PlayerService player = new Player();
			player.init(env, 7, 2, Dir.N, 100, 10);
			es.init(env, player);
			CowService cow1 = new CowServiceContract(new Cow());
			cow1.init(env, 0, 5, Dir.N);
			env.setCellContent(0, 5, cow1);
			
			int oldsize = es.getEntities().size();
			List<MobService> oldentities = new ArrayList<>(es.getEntities());
			
			es.addEntity(cow1);
			assertTrue(oldsize + 1 == es.getEntities().size());
			for(int i = 0; i < oldsize; i++) {
				assertTrue(oldentities.get(i) == es.getEntity(i));
			}
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	
	@Test
	public void removeEntityTransTest_1() {
		//cas positif
		try {
			PlayerService player = new Player();
			player.init(env, 7, 2, Dir.N, 100, 10);
			es.init(env, player);
			CowService cow1 = new CowServiceContract(new Cow());
			cow1.init(env, 0, 5, Dir.N);
			env.setCellContent(0, 5, cow1);
			es.addEntity(cow1);
			
			CowService cow2 = new CowServiceContract(new Cow());
			cow2.init(env, 3, 4, Dir.N);
			env.setCellContent(3, 4, cow2);
			es.addEntity(cow2);
			
			CowService cow3 = new CowServiceContract(new Cow());
			cow3.init(env, 8, 4, Dir.N);
			env.setCellContent(8, 4, cow3);
			es.addEntity(cow3);
			
			
			int oldsize = es.getEntities().size();
			List<MobService> oldentities = new ArrayList<>(es.getEntities());
			
			es.removeEntity(0);
			assertTrue(oldsize - 1 == es.getEntities().size());
			for(int i = 1; i < es.getEntities().size(); i++) {
				assertTrue(oldentities.get(i) == es.getEntity(i-1));
			}
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void removeEntityPreTest_1() {
		//cas positif
		try {
			PlayerService player = new Player();
			player.init(env, 7, 2, Dir.N, 100, 10);
			es.init(env, player);
			CowService cow = new CowServiceContract(new Cow());
			cow.init(env, 0, 5, Dir.N);
			env.setCellContent(0, 5, cow);
			es.addEntity(cow);
			es.removeEntity(0);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	
	@Test
	public void removeEntityPreTest_2() {
		//cas negatif
		try {
			PlayerService player = new Player();
			player.init(env, 7, 2, Dir.N, 100, 10);
			es.init(env, player);
			es.removeEntity(52);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	@Test
	public void stepPreTest_1() {
		//cas positif
		try {
			PlayerService player = new Player();
			player.init(env, 7, 2, Dir.N, 100, 10);
			es.init(env, player);
			CowService cow = new CowServiceContract(new Cow());
			cow.init(env, 0, 5, Dir.N, 3, 10);
			env.setCellContent(0, 5, cow);
			es.addEntity(cow);
			es.step();
		}catch(PreconditionError pe) {
			pe.printStackTrace();
		}
	}
}
