/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.ByteArrayOutputStream;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.exec.*;

/**
 *
 * @author djordje
 */
public class ProcessExecutor {

    public static String executeYacasCommand(String command) {
        try {
            String location = null;
            if (SystemUtils.IS_OS_WINDOWS) {
                location = System.getProperty("user.dir") + "\\yacas\\win64\\bin\\yacas.exe";
            } else if (SystemUtils.IS_OS_LINUX) {
                //currently only ubuntu is supported. Minimal version: 14.04
                location = System.getProperty("user.dir") + "\\yacas\\ubuntu14.04x64\\bin\\yacas";
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CommandLine commandline = new CommandLine(location);
            commandline.addArgument("-i");
            commandline.addArgument(command);
            DefaultExecutor exec = new DefaultExecutor();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            exec.setStreamHandler(streamHandler);
            exec.execute(commandline);
            return (outputStream.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
