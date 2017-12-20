package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * CommandPromptUtil - runs the CLI command and returns the
 * output in different ways
 */
public class CommandPromptUtil {

    public String runCommandThroughProcess(String command) throws IOException {

        BufferedReader br = getBufferedReader(command);
        String allLines = null;
        String line = "";
        while ( (line = br.readLine()) != null){
            allLines = allLines + line + "\n";
        }
        return allLines;
    }

    public List<String> runCommand(String command) throws IOException {
        BufferedReader br = getBufferedReader(command);
        String line = "";
        List<String> allLines = new ArrayList<>();
        while((line = br.readLine()) != null){
            allLines.add(line.replaceAll("[\\[\\](){}]","_").split("_")[1]);
        }
       return allLines;
    }


    /**
     * Execute the given shell command given in argument and returns
     * the command output in BufferedReader object
     * @param command
     * @return
     * @throws IOException
     */
    private BufferedReader getBufferedReader(String command) throws IOException {
        List<String> commands = new ArrayList<>();
        commands.add("/bin/sh");
        commands.add("-c");
        commands.add(command);
        ProcessBuilder builder = new ProcessBuilder(commands);
        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);
    }

    public String runProcessCommandToGetDeviceID(String command) throws IOException {
        BufferedReader br = getBufferedReader(command);
        String allLines = null;
        String line = "";
        while ( (line = br.readLine()) != null){
            allLines = allLines.trim() + line.trim() + "\n";
        }
        return allLines;
    }
}
