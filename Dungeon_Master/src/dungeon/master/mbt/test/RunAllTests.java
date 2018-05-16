package dungeon.master.mbt.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	MapServiceTest.class,
	MobTest.class,
	EditMapServiceTest.class,
	EnvironmentServiceTest.class})
public class RunAllTests {

}
