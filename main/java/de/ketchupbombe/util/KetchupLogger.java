package de.ketchupbombe.util;

import de.ketchupbombe.Main;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class KetchupLogger {

    public static void log(LogType arg0, Object arg1) {
        System.out.println(logString(arg0, arg1));
        Main.LOG_FILE.writeLineWithDate(logString(arg0, arg1));
    }

    private static String logString(LogType arg0, Object arg1) {
        switch (arg0) {
            case INFO:
                return "[INFO] " + arg1;
            case WARNING:
                return "[WARNING] " + arg1;
            case ERROR:
                return "[ERROR] " + arg1;
            default:
                return null;

        }
    }

    public static String logCommand(boolean succeed, MessageReceivedEvent event, String command) {
        try {
            String msg;
            if (!succeed) {
                msg = logString(LogType.INFO, event.getAuthor().getName() + " performed command \'" + command + "\' successfully in channel #" + event.getTextChannel().getName() + "!");
                System.out.println(msg);
                Main.LOG_FILE.writeLineWithDate(msg);
            } else {
                msg = logString(LogType.WARNING, event.getAuthor().getName() + " tried to perform command \'" + command + "\' in channel #" + event.getTextChannel().getName() + "!");
                System.out.println(msg);
                Main.LOG_FILE.writeLineWithDate(msg);
            }
            return msg;
        } catch (NullPointerException ignored) {
        }
        return "ERROR";
    }

    public enum LogType {
        INFO,
        WARNING,
        ERROR
    }

}
