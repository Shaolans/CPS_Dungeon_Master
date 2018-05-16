package dungeon.master.mbt.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.EditMap;
import dungeon.master.contracts.EditMapServiceContract;
import dungeon.master.enumerations.Cell;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.EditMapService;

public class EditMapServiceTest {
	private EditMapService ems;
	
	@Before
	public void beforeTests() {
		ems = new EditMapServiceContract(new EditMap());
	}
	
	
	@After
	public void afterTests() {
		ems = null;
	}
	
	@Test
	public void isReadyTransitionTest_1() {
		//cas positif
		try {
			ems.init(10, 20);
			assertTrue(ems.isReady());
		}catch(PreconditionError ex) {
			fail();
		}catch(InvariantError ie) {
			fail();
		}
		
	}
	
	@Test
	public void isReadyTransitionTest_2() {
		//cas negatif
		try {
			ems.init(10, 20);
			ems.setNature(0, 0, Cell.IN);
			ems.setNature(0, 19, Cell.OUT);
			assertTrue(!ems.isReady());
		}catch(PreconditionError ex) {
			fail();
		}catch(InvariantError ie) {
			fail();
		}
		
	}
	
	@Test
	public void isReachableTransitionTest_1() {
		//cas positif
		try {
			ems.init(10, 20);
			ems.setNature(0, 0, Cell.IN);
			ems.setNature(0, 19, Cell.OUT);
			assertTrue(ems.isReachable(0, 0, 0, 19));
		}catch(PreconditionError ex) {
			fail();
		}catch(InvariantError ie) {
			fail();
		}
		
	}
	@Test
	public void setNatureTransitionTest_1() {
		//cas positif
		Cell copy[][] = new Cell[10][20];
		try {
			ems.init(10, 20);
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 20; j++) {
					copy[i][j] = ems.getCellNature(i, j);
				}
			}
			
			
			ems.setNature(5, 5, Cell.IN);
		}catch(PreconditionError ex) {
			fail();
		}
		
		assertTrue(ems.getCellNature(5, 5) == Cell.IN);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 20; j++) {
				if(i != 5 || j != 5) {
					assertTrue(ems.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	
	@Test
	public void setNaturePreTest_1() {
		//cas positif
		try {
			ems.init(10, 20);
			
			ems.setNature(0, 0, Cell.IN);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void setNaturePreTest_2() {
		//cas positif
		try {
			ems.init(10, 20);
			
			ems.setNature(0, -1, Cell.IN);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	
	@Test
	public void isReachablePreTest_1() {
		//cas positif
		try {
			ems.init(10, 20);
			ems.setNature(0, 0, Cell.IN);
			ems.setNature(9, 19, Cell.OUT);
			
			ems.isReachable(0, 0, 9, 19);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void isReachablePreTest_2() {
		//cas negatif
		try {
			ems.init(10, 20);
			ems.setNature(0, 0, Cell.IN);
			ems.setNature(9, 19, Cell.OUT);
			ems.setNature(5, 0, Cell.WLL);
			ems.setNature(8, 19, Cell.WLL);
			
			ems.isReachable(5, 0, 8, 19);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	
	@Test
	public void initPreTest_1() {
		//cas positif
		try {
			ems.init(10, 20);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void initPreTest_2() {
		//cas negatif
		try {
			ems.init(0, 20);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void initPreTest_3() {
		//cas negatif
		try {
			ems.init(-20, 20);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void initTransitionTest_1() {
		try {
			ems.init(10,5);
		}catch(PreconditionError pe) {
			fail();
		}catch(InvariantError ie) {
			fail();
		}
		
		assertTrue(ems.getWidth() == 10 &&
				ems.getHeight() == 5);
	}
	
	
	
	@Test
	public void getCellNaturePreTest_1() {
		//cas positif
		try {
			ems.init(10, 15);
			ems.getCellNature(0, 0);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void getCellNaturePreTest_2() {
		//cas positif
		try {
			ems.init(10, 15);
			ems.getCellNature(4, 6);
		}catch(PreconditionError ex) {
			fail();
		}
	}
	
	@Test
	public void getCellNaturePreTest_3() {
		//cas negatif
		try {
			ems.init(10, 15);
			ems.getCellNature(-1, 6);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	@Test
	public void getCellNaturePreTest_4() {
		//cas negatif
		try {
			ems.init(10, 15);
			ems.getCellNature(19, 6);
			fail();
		}catch(PreconditionError ex) {
			
		}
	}
	
	
	@Test
	public void openDoorPreTest_1() {
		//cas positif
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DNC;
			
			ems.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void openDoorPreTest_2() {
		//cas positif
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DWC;
			
			ems.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void openDoorPreTest_3() {
		//cas negatif
		try {
			ems.init(10, 15);
			
			ems.openDoor(2, 5);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	@Test
	public void openDoorTransitionTest_1() {
		//cas positif
		
		Cell copy[][] = new Cell[10][15];
		
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DNC;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = ems.getCellNature(i, j);
				}
			}
			
			ems.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(ems.getCellNature(2, 5) == Cell.DNO);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(ems.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	@Test
	public void openDoorTransitionTest_2() {
		//cas positif
		Cell copy[][] = new Cell[10][15];
		
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DWC;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = ems.getCellNature(i, j);
				}
			}
			
			ems.openDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(ems.getCellNature(2, 5) == Cell.DWO);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(ems.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	
	
	@Test
	public void closeDoorPreTest_1() {
		//cas positif
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DWO;
			
			ems.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void closeDoorPreTest_2() {
		//cas positif
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DNO;
			
			ems.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
	}
	
	@Test
	public void closeDoorPreTest_3() {
		//cas negatif
		try {
			ems.init(10, 15);
			ems.closeDoor(2, 5);
			fail();
		}catch(PreconditionError pe) {
			
		}
	}
	
	
	@Test
	public void closeDoorTransitionTest_1() {
		//cas positif
		
		Cell copy[][] = new Cell[10][15];
		
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DNO;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = ems.getCellNature(i, j);
				}
			}
			
			ems.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(ems.getCellNature(2, 5) == Cell.DNC);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(ems.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
	
	@Test
	public void closeDoorTransitionTest_2() {
		//cas positif
		Cell copy[][] = new Cell[10][15];
		
		try {
			ems.init(10, 15);
			ems.getArray()[2][5] = Cell.DWO;
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 15; j++) {
					copy[i][j] = ems.getCellNature(i, j);
				}
			}
			
			ems.closeDoor(2, 5);
		}catch(PreconditionError pe) {
			fail();
		}
		
		assertTrue(ems.getCellNature(2, 5) == Cell.DWC);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 15; j++) {
				if(i != 2 || j != 5) {
					assertTrue(ems.getCellNature(i, j) == copy[i][j]);
				}
			}
		}
	}
}
