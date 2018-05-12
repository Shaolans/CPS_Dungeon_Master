package dungeon.master.mbt.test;

import org.junit.After;
import org.junit.Before;

import dungeon.master.components.Cow;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.services.CowService;

public class CowServiceTest {
	private CowService cow;
	
	@Before
	public void beforeTests() {
		cow = new CowServiceContract(new Cow());
	}
	
	
	@After
	public void afterTests() {
		cow = null;
	}
	
	
}
