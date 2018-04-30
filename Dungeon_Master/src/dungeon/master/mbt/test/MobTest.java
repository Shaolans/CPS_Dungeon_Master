package dungeon.master.mbt.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Environment;
import dungeon.master.components.Mob;
import dungeon.master.contracts.MobServiceContract;
import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class MobTest {
	private MobService mob;
	
	@Before
	public void beforeTests() {
		mob = new MobServiceContract(new Mob());
	}
	
	@After
	public void afterTests() {
		mob = null;
	}
	
	@Test
	public void initPreTest_1() {
		//cas positif
		try {
			EnvironmentService env = new Environment();
			env.init(10, 15);
			
			mob.init(env, 5, 7, Dir.N);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void initPreTest_2() {
		//cas negatif
		try {
			EnvironmentService env = new Environment();
			env.init(10, 15);
			
			mob.init(env, 19, 7, Dir.N);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	@Test
	public void initTransitionTest_1() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		
		mob.init(env, 6, 9, Dir.N);
		
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.N);
	}
	
	@Test
	public void forwardTransitionTest_1() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.N);
		
		mob.forward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 10 &&
				mob.getFace() == Dir.N);
		
	}
	
	@Test
	public void forwardTransitionTest_2() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.E);
		
		mob.forward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 5 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.E);
		
	}
	
	@Test
	public void forwardTransitionTest_3() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.S);
		
		mob.forward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 8 &&
				mob.getFace() == Dir.S);
		
	}
	
	@Test
	public void forwardTransitionTest_4() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.W);
		
		mob.forward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 7 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.W);
		
	}
	
	
	@Test
	public void backwardTransitionTest_1() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.N);
		
		mob.backward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 8 &&
				mob.getFace() == Dir.N);
		
	}
	
	@Test
	public void  backwardTransitionTest_2() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.E);
		
		mob.forward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 5 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.E);
		
	}
	
	@Test
	public void  backwardTransitionTest_3() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.S);
		
		mob.backward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 10 &&
				mob.getFace() == Dir.S);
		
	}
	
	@Test
	public void  backwardTransitionTest_4() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.W);
		
		mob.forward();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 7 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.W);
		
	}
	
	
	@Test
	public void strafeLTransitionTest_1() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.N);
		
		mob.strafeL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 7 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.N);
		
	}
	
	@Test
	public void strafeLTransitionTest_2() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.E);
		
		mob.strafeL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 10 &&
				mob.getFace() == Dir.E);
		
	}
	
	@Test
	public void strafeLTransitionTest_3() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.S);
		
		mob.strafeL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 5 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.S);
		
	}
	
	@Test
	public void strafeLTransitionTest_4() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.W);
		
		mob.strafeL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 8 &&
				mob.getFace() == Dir.W);
		
	}
	
	
	@Test
	public void strafeRTransitionTest_1() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.N);
		
		mob.strafeR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 5 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.N);
		
	}
	
	@Test
	public void strafeRTransitionTest_2() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.E);
		
		mob.strafeR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 8 &&
				mob.getFace() == Dir.E);
		
	}
	
	@Test
	public void strafeRTransitionTest_3() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.S);
		
		mob.strafeR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 7 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.S);
		
	}
	
	@Test
	public void strafeRTransitionTest_4() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.W);
		
		mob.strafeR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 10 &&
				mob.getFace() == Dir.W);
		
	}
	
	@Test
	public void TurnLTransitionTest_1() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.N);
		
		mob.turnL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.W);
		
	}
	
	@Test
	public void  TurnLTransitionTest_2() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.E);
		
		mob.turnL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.N);
		
	}
	
	@Test
	public void  TurnLTransitionTest_3() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.S);
		
		mob.turnL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.E);
		
	}
	
	@Test
	public void  TurnLTransitionTest_4() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.W);
		
		mob.turnL();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.S);
		
	}
	
	@Test
	public void TurnRTransitionTest_1() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.N);
		
		mob.turnR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.E);
		
	}
	
	@Test
	public void  TurnRTransitionTest_2() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.E);
		
		mob.turnR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.S);
		
	}
	
	@Test
	public void  TurnRTransitionTest_3() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.S);
		
		mob.turnR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.W);
		
	}
	
	@Test
	public void  TurnRTransitionTest_4() {
		EnvironmentService env = new Environment();
		env.init(10, 15);
		mob.init(env, 6, 9, Dir.W);
		
		mob.turnR();
		assertTrue(mob.getEnv() == env &&
				mob.getCol() == 6 &&
				mob.getRow() == 9 &&
				mob.getFace() == Dir.N);
		
	}
}
