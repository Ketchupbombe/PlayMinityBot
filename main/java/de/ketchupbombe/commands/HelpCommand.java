package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.commands.core.CommandManager;
import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.RoleManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Map;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class HelpCommand extends Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (RoleManager.isAllowedForCommand(this, event.getMember()))
            return true;
        this.forbiddenChannels.add(Constants.TEXT_LOBBY_ID);
        this.forbiddenChannels.add(Constants.TEXT_INFO_ID);
        return false;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {
        event.getAuthor().openPrivateChannel().queue(privateChannel -> {
            MessageBuilder msgBuilder = new MessageBuilder().setContent("Du kannst folgende Kommandos benutzen:" + System.getProperty("line.separator"));
            for (Map.Entry<String, Command> commands: CommandManager.commands.entrySet()) {
                if (commands.getValue().getPermissionValue() < RoleManager.getHighestPermissionValueByMember(event.getMember())) {
                    String cmdMessage = "-> " + commands.getKey() + ": " + commands.getValue().help() + System.getProperty("line.separator");
                    msgBuilder.append(cmdMessage);
                }
            }
            Message msg = msgBuilder.build();
            privateChannel.sendMessage(msg).queue();
        });
    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "help");
    }

    @Override
    public String help() {
        return "Information about all commands";
    }
}
