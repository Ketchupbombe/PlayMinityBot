package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.RoleManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class DeveloperCommand extends Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (RoleManager.isAllowedForCommand(this, event.getMember()))
            return true;
        this.aliases.add("dev");
        return false;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {

    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "Dev");
    }
}
