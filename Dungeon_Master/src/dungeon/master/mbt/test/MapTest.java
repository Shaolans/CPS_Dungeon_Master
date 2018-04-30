package dungeon.master.mbt.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Map;
import dungeon.master.contracts.MapServiceContract;
import dungeon.master.enumerations.Cell;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.MapService;

public class MapTest {
	private MapService map;
	
	@Before
	public void beforeTests() {
		map = new MapServiceContract(new Map());
	}
	
	
	@After
	public void afterTests() {
		map = null;
	}
	
	@Test
	public void initPreTest_1() {
		//cas positif
		try {
			map.init(10, 20);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void initPreTest_2() {
		//cas negatif
		try {
			map.init(0, 20);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void initPreTest_3() {
		//cas negatif
		try {
			map.init(-20, 20);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void initTransitionTest_1() {
		try {
			map.init(10,5);
		}catch(PreconditionError pe) {
			fail();
		}catch(InvariantError ie) {
			fail();
		}
		
		assertTrue(map.getWidth() == 10 &&
				map.getHeight() == 5);
	}
	
	
	
	@Test
	public void getCellNaturePreTest_1() {
		//cas positif
		try {
			map.init(10, 15);
			map.getCellNature(0, 0);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void getCellNaturePreTest_2() {
		//cas positif
		try {
			map.init(10, 15);
			map.getCellNature(4, 6);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void getCellNaturePreTest_3() {
		//cas negatif
		try {
			map.init(10, 15);
			map.getCellNature(-1, 6);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void getCellNaturePreTest_4() {
		//cas negatif
		try {
			map.init(10, 15);
			map.getCellNature(19, 6);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	
	@Test
	public void openDoorPreTest_1() {
		//cas positif
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DNC;
			
			map.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void openDoorPreTest_2() {
		//cas positif
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DWC;
			
			map.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void openDoorPreTest_3() {
		//cas negatif
		try {
			map.init(10, 15);
			
			map.openDoor(2, 5);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	@Test
	public void openDoorTransitionTest_1() {
		//cas positif
		
		Cell copy[][] = new Cell[10][15];
		
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DNC;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = map.getCellNature(i, j);
				}
			}
			
			map.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(map.getCellNature(2, 5) == Cell.DNO);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(map.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	@Test
	public void openDoorTransitionTest_2() {
		//cas positif
		Cell copy[][] = new Cell[10][15];
		
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DWC;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = map.getCellNature(i, j);
				}
			}
			
			map.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(map.getCellNature(2, 5) == Cell.DWO);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(map.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	
	
	@Test
	public void closeDoorPreTest_1() {
		//cas positif
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DWO;
			
			map.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void closeDoorPreTest_2() {
		//cas positif
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DNO;
			
			map.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void closeDoorPreTest_3() {
		//cas negatif
		try {
			map.init(10, 15);
			map.closeDoor(2, 5);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	
	@Test
	public void closeDoorTransitionTest_1() {
		//cas positif
		
		Cell copy[][] = new Cell[10][15];
		
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DNO;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = map.getCellNature(i, j);
				}
			}
			
			map.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(map.getCellNature(2, 5) == Cell.DNC);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(map.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	@Test
	public void closeDoorTransitionTest_2() {
		//cas positif
		Cell copy[][] = new Cell[10][15];
		
		try {
			map.init(10, 15);
			map.getArray()[2][5] = Cell.DWO;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = map.getCellNature(i, j);
				}
			}
			
			map.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(map.getCellNature(2, 5) == Cell.DWC);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(map.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
}
