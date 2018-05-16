package dungeon.master.mbt.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Environment;
import dungeon.master.components.Mob;
import dungeon.master.contracts.EnvironmentServiceContract;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Option;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EnvironmentService;
import dungeon.master.services.MobService;

public class EnvironmentServiceTest {
	private EnvironmentService env;
	
	
	@Before
	public void beforeTests() {
		env = new EnvironmentServiceContract(new Environment());
	}
	
	
	@After
	public void afterTests() {
		env = null;
	}
	
	@Test
	public void setCellContentTransitionTest_1() {
		//cas positif
		try {
			env.init(10, 20);
			Mob m = new Mob();
			@SuppressWarnings("unchecked")
			Option<MobService> map[][] = new Option[10][20];
			for(int i = 0; i < map.length; i++){
				for(int j = 0; j < map[0].length; j++){
					map[i][j] = env.getCellContent(i, j);
				}
			}
			env.setCellContent(5, 5, m);
			
			assertTrue(env.getCellContent(5, 5).getValue() == m);
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 20; j++) {
					if(i != 5 || j != 5) {
						assertTrue(map[i][j].getValue() == env.getCellContent(i, j).getValue());
					}
				}
			}
		}catch(InvariantError ie) {
			fail();
		}
	}
	
	
	@Test
	public void setCellContentPreTest_1() {
		//cas positif
		try {
			env.init(10, 20);
			env.setCellContent(1, 2, new Mob());
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void setCellContentPreTest_2() {
		//cas negatif
		try {
			env.init(10, 20);
			env.setCellContent(1, 96, new Mob());
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	
	@Test
	public void initPreTest_1() {
		//cas positif
		try {
			env.init(10, 20);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void initPreTest_2() {
		//cas negatif
		try {
			env.init(0, 20);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void initPreTest_3() {
		//cas negatif
		try {
			env.init(-20, 20);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void initTransitionTest_1() {
		try {
			env.init(10,5);
		}catch(PreconditionError pe) {
			fail();
		}catch(InvariantError ie) {
			fail();
		}
		
		assertTrue(env.getWidth() == 10 &&
				env.getHeight() == 5);
	}
	
	
	
	@Test
	public void getCellNaturePreTest_1() {
		//cas positif
		try {
			env.init(10, 15);
			env.getCellNature(0, 0);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void getCellNaturePreTest_2() {
		//cas positif
		try {
			env.init(10, 15);
			env.getCellNature(4, 6);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void getCellNaturePreTest_3() {
		//cas negatif
		try {
			env.init(10, 15);
			env.getCellNature(-1, 6);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void getCellNaturePreTest_4() {
		//cas negatif
		try {
			env.init(10, 15);
			env.getCellNature(19, 6);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	
	@Test
	public void openDoorPreTest_1() {
		//cas positif
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DNC;
			
			env.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void openDoorPreTest_2() {
		//cas positif
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DWC;
			
			env.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void openDoorPreTest_3() {
		//cas negatif
		try {
			env.init(10, 15);
			
			env.openDoor(2, 5);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	@Test
	public void openDoorTransitionTest_1() {
		//cas positif
		
		Cell copy[][] = new Cell[10][15];
		
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DNC;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = env.getCellNature(i, j);
				}
			}
			
			env.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(env.getCellNature(2, 5) == Cell.DNO);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(env.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	@Test
	public void openDoorTransitionTest_2() {
		//cas positif
		Cell copy[][] = new Cell[10][15];
		
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DWC;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = env.getCellNature(i, j);
				}
			}
			
			env.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(env.getCellNature(2, 5) == Cell.DWO);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(env.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	
	
	@Test
	public void closeDoorPreTest_1() {
		//cas positif
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DWO;
			
			env.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void closeDoorPreTest_2() {
		//cas positif
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DNO;
			
			env.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void closeDoorPreTest_3() {
		//cas negatif
		try {
			env.init(10, 15);
			env.closeDoor(2, 5);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	
	@Test
	public void closeDoorTransitionTest_1() {
		//cas positif
		
		Cell copy[][] = new Cell[10][15];
		
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DNO;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = env.getCellNature(i, j);
				}
			}
			
			env.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			pe.printStackTrace();
			fail();
		}
		
		assertTrue(env.getCellNature(2, 5) == Cell.DNC);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(env.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	@Test
	public void closeDoorTransitionTest_2() {
		//cas positif
		Cell copy[][] = new Cell[10][15];
		
		try {
			env.init(10, 15);
			env.getArray()[2][5] = Cell.DWO;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = env.getCellNature(i, j);
				}
			}
			
			env.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(env.getCellNature(2, 5) == Cell.DWC);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(env.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
}
