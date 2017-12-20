package manager;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;

public class AppiumServerManagerTest {

    @Test
    public void testAppiumServerManagerStarts() throws IOException {
        AppiumServerManager serverManager = new AppiumServerManager();
        serverManager.startAppiumServer();

        assertThat("Appium server has not started",serverManager.getAppiumUrl().toString(),
                Matchers.containsString("127.0.0.1"));

        serverManager.stopAppiumServer();
    }
}