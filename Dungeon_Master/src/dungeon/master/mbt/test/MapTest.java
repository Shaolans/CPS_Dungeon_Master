package dungeon.master.mbt.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Map;
import dungeon.master.contracts.MapServiceContract;
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
	
	
	
}
