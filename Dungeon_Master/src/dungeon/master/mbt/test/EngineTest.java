package dungeon.master.mbt.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Cow;
import dungeon.master.components.EditMap;
import dungeon.master.components.Engine;
import dungeon.master.components.Environment;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.contracts.EngineServiceContract;
import dungeon.master.contracts.EnvironmentServiceContract;
import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.CowService;
import dungeon.master.services.EngineService;
import dungeon.master.services.EnvironmentService;

public class EngineTest {
	private EngineService es;
	private EnvironmentService env;
	
	@Before
	public void beforeTests() {
		es = new EngineServiceContract(new Engine());
		env = new EnvironmentServiceContract(new Environment());
		env.init(10, 10);
	}
	
	
	@After
	public void afterTests() {
		es = null;
		env = null;
	}
	
	@Test
	public void removeEntityPreTest_1() {
		//cas positif
		try {
			es.init(env);
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
		//cas negatof
		try {
			es.init(env);
			es.removeEntity(52);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	@Test
	public void stepPreTest_1() {
		//cas positif
		try {
			es.init(env);
			CowService cow = new CowServiceContract(new Cow());
			cow.init(env, 0, 5, Dir.N, 3);
			env.setCellContent(0, 5, cow);
			es.addEntity(cow);
			es.step();
		}catch(PreconditionError pe) {
			pe.printStackTrace();
		}
	}
}
