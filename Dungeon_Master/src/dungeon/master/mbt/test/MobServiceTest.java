package dungeon.master.mbt.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Cow;
import dungeon.master.components.Environment;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.contracts.EnvironmentServiceContract;
import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class MobServiceTest {
	protected MobService mob;
	protected EnvironmentService env;
	
	@Before
	public void beforeTests() {
		mob = new CowServiceContract(new Cow());
		env = new EnvironmentServiceContract(new Environment());
		env.init(10, 15);
	}
	
	@After
	public void afterTests() {
		mob = null;
	}
	
	@Test
	public void strafeLTransitionTest_1() {
		try {
			mob.init(env, 5, 7, Dir.N);
			mob.strafeL();
			System.out.println(mob.getCol()+" "+mob.getRow());
			assertTrue(mob.getCol() == 6 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			ie.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void strafeLTransitionTest_2() {
		try {
			mob.init(env, 5, 7, Dir.S);
			mob.strafeL();
			assertTrue(mob.getCol() == 4 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void strafeLTransitionTest_3() {
		try {
			mob.init(env, 5, 7, Dir.W);
			mob.strafeL();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 6);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void strafeLTransitionTest_4() {
		try {
			mob.init(env, 5, 7, Dir.E);
			mob.strafeL();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 8);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void strafeRTransitionTest_1() {
		try {
			mob.init(env, 5, 7, Dir.N);
			mob.strafeR();
			assertTrue(mob.getCol() == 4 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void strafeRTransitionTest_2() {
		try {
			mob.init(env, 5, 7, Dir.S);
			mob.strafeR();
			assertTrue(mob.getCol() == 6 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void strafeRTransitionTest_3() {
		try {
			mob.init(env, 5, 7, Dir.W);
			mob.strafeR();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 8);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void strafeRTransitionTest_4() {
		try {
			mob.init(env, 5, 7, Dir.E);
			mob.strafeR();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 6);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void TurnRTransitionTest_1() {
		try {
			mob.init(env, 5, 7, Dir.N);
			mob.turnR();
			assertTrue(mob.getFace() == Dir.E);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void TurnRTransitionTest_2() {
		try {
			mob.init(env, 5, 7, Dir.W);
			mob.turnR();
			assertTrue(mob.getFace() == Dir.N);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	@Test
	public void TurnRTransitionTest_3() {
		try {
			mob.init(env, 5, 7, Dir.S);
			mob.turnR();
			assertTrue(mob.getFace() == Dir.W);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void TurnRTransitionTest_4() {
		try {
			mob.init(env, 5, 7, Dir.E);
			mob.turnR();
			assertTrue(mob.getFace() == Dir.S);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	@Test
	public void TurnLTransitionTest_1() {
		try {
			mob.init(env, 5, 7, Dir.N);
			mob.turnL();
			assertTrue(mob.getFace() == Dir.W);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void TurnLTransitionTest_2() {
		try {
			mob.init(env, 5, 7, Dir.W);
			mob.turnL();
			assertTrue(mob.getFace() == Dir.S);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	@Test
	public void TurnLTransitionTest_3() {
		try {
			mob.init(env, 5, 7, Dir.S);
			mob.turnL();
			assertTrue(mob.getFace() == Dir.E);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	@Test
	public void TurnLTransitionTest_4() {
		try {
			mob.init(env, 5, 7, Dir.E);
			mob.turnL();
			assertTrue(mob.getFace() == Dir.N);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	@Test
	public void forwardTransitionTest_1() {
		try {
			mob.init(env, 5, 7, Dir.N);
			mob.forward();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 8);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void forwardTransitionTest_2() {
		try {
			mob.init(env, 5, 7, Dir.E);
			mob.forward();
			assertTrue(mob.getCol() == 4 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void forwardTransitionTest_3() {
		try {
			mob.init(env, 5, 7, Dir.S);
			mob.forward();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 6);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void forwardTransitionTest_4() {
		try {
			mob.init(env, 5, 7, Dir.W);
			mob.forward();
			assertTrue(mob.getCol() == 6 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	
	
	
	
	
	@Test
	public void backwardTransitionTest_1() {
		try {
			mob.init(env, 5, 7, Dir.S);
			mob.backward();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 8);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void backwardTransitionTest_2() {
		try {
			mob.init(env, 5, 7, Dir.W);
			mob.backward();
			assertTrue(mob.getCol() == 4 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void backwardTransitionTest_3() {
		try {
			mob.init(env, 5, 7, Dir.N);
			mob.backward();
			assertTrue(mob.getCol() == 5 && mob.getRow() == 6);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	@Test
	public void backwardTransitionTest_4() {
		try {
			mob.init(env, 5, 7, Dir.E);
			mob.backward();
			assertTrue(mob.getCol() == 6 && mob.getRow() == 7);
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	
	@Test
	public void initPreTest_1() {
		//cas positif
		try {
			mob.init(env, 3, 5, Dir.N);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	
	@Test
	public void initPreTest_2() {
		//cas negatif
		try {
			mob.init(env, 25, 5, Dir.N);
			fail();
		}catch(PreconditionError pe) {
		}
	}
	
	
}
