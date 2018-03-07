package de.ketchupbombe.commands.core;

import de.ketchupbombe.util.images.ImageCategory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class CommandManager {

    public static HashMap<String, Command> commands = new HashMap<>();

    public static void handleCommand(CommandContainer container) {
        if (commands.containsKey(container.command)) {
            //local command
            Command command = commands.get(container.command);
            command.setContainer(container);
            boolean safe = command.called(container.args, container.event);

            for (long l : command.forbiddenChannels) {
                if (container.event.getTextChannel().getIdLong() == l) {
                    safe = true;
                }
            }

            if (!safe) {
                command.onCommand(container.args, container.event);
                command.executed(false, container.event);
            } else
                command.executed(true, container.event);
        }
    }

    public static void registerCommand(String arg0, Command arg1, int arg2) {
        arg1.setPermissionValue(arg2);
        commands.put(arg0, arg1);
        registerCommandAliases(arg1, arg1.aliases);
    }
    private static void registerCommandAliases(Command arg0, ArrayList<String> arg1){
        if (!(arg1.isEmpty())) {
            for (String s : arg1) {
                commands.put(s, arg0);
            }
        }
    }

    public static CommandContainer parseToContainer(String raw, MessageReceivedEvent event) {
        String withoutPrefix = raw.replaceFirst(".", "");
        String[] splitted = withoutPrefix.split(" ");

        String command = splitted[0].toLowerCase();
        // transform image-alias in main command
        ImageCategory category = ImageCategory.getCategoryByCmd(command);
        if (category != null) {
            command = category.getCmd();
        }
        ArrayList<String> splitArray = new ArrayList<>();
        Collections.addAll(splitArray, splitted);
        String[] args = new String[splitArray.size() - 1];
        splitArray.subList(1, splitArray.size()).toArray(args);

        return new CommandContainer(raw, command, args, event);
    }

    public static class CommandContainer {

        final String raw;
        final String command;
        final String[] args;
        final MessageReceivedEvent event;

        private CommandContainer(String raw, String command, String[] args, MessageReceivedEvent event) {
            this.raw = raw;
            this.command = command;
            this.args = args;
            this.event = event;
        }

        public String getRaw() {
            return raw;
        }

        public String getCommand() {
            return command;
        }

        public String[] getArgs() {
            return args;
        }

        public MessageReceivedEvent getEvent() {
            return event;
        }
    }

}
