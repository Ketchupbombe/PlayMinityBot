package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.ReturnMessage;
import de.ketchupbombe.util.RoleManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class PingCommand extends Command {

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
        event.getTextChannel().sendMessage(new MessageBuilder().setContent("Pong!").build()).queue();
    }

    @Override
    public void executed(boolean succeseed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(succeseed, event, "ping");
    }

    @Override
    public String help() {
        return "to check if Bot is online";
    }
}
