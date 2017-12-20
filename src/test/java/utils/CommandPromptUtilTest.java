package utils;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;

import static org.hamcrest.MatcherAssert.assertThat;

public class CommandPromptUtilTest {


    @Test
    public void testRunCommandThroughProcess() throws Exception {

        CommandPromptUtil commandPromptUtil = new CommandPromptUtil();

        assertThat(commandPromptUtil.runCommandThroughProcess("date"),
                Matchers.containsString(Year.now().toString()));

    }

    @Test
    public void testRunCommand() throws Exception {

        CommandPromptUtil commandPromptUtil = new CommandPromptUtil();
        commandPromptUtil.runCommand("adb start-server");
    }

    @Test
    public void testRunProcessCommandToGetDeviceID() throws Exception {
    }

    @Test
    public void testCommandLineOutput() throws IOException {



    }

}