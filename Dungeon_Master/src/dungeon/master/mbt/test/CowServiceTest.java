package dungeon.master.mbt.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Cow;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.CowService;

public class CowServiceTest extends MobServiceTest {
	private CowService cow;
	
	@Before
	public void beforeTests() {
		super.beforeTests();
		cow = new CowServiceContract(new Cow());
		mob = cow;
	}
	
	
	@After
	public void afterTests() {
		super.afterTests();
		cow = null;
	}
	
	@Test
	public void initPreTest_1(){
		//cas positif
		try{
			cow.init(env, 5, 6, Dir.N);
		}catch(PreconditionError pe){
			fail();
		}
	}
	
	@Test
	public void initPreTest_2(){
		//cas negatif
		try{
			cow.init(env, 55, 6, Dir.N);
			fail();
		}catch(PreconditionError pe){
			
		}
	}
	
	@Test
	public void stepTransitionTest_1(){
		try{
			cow.init(env, 5, 7, Dir.N);
			cow.step();
			assertTrue(cow.getCol() >= 4 && cow.getCol() <= 6 &&
					cow.getRow() >= 6 && cow.getCol() <= 8);
		}catch(InvariantError ie){
			fail();
		}
		
	}
}
