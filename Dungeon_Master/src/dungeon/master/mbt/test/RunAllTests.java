package dungeon.master.mbt.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CowServiceTest.class,
	EngineServiceTest.class,
	MapServiceTest.class,
	MobServiceTest.class,
	EditMapServiceTest.class,
	EnvironmentServiceTest.class,
	PlayerServiceTest.class})
public class RunAllTests {

}
