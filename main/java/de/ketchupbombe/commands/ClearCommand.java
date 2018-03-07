package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.ReturnMessage;
import de.ketchupbombe.util.RoleManager;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class ClearCommand extends Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (RoleManager.isAllowedForCommand(this, event.getMember()))
            return true;
        return false;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {
        if (args.length == 1) {
            event.getMessage().delete().queue();
            int clearNumb = 0;
            try {
                clearNumb = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
            }

            if (clearNumb >= 1 && clearNumb <= 100) {

                if (clearNumb == 1) {
                    event.getTextChannel().sendMessage(".");
                    clearNumb++;
                }
                MessageHistory history = new MessageHistory(event.getTextChannel());
                List<Message> messages = history.retrievePast(clearNumb).complete();
                List<Message> withoutPinnedMessages = new ArrayList<>();
                for (Message msg : messages) {
                    if (msg.isPinned()) continue;

                    withoutPinnedMessages.add(msg);
                }
                try {

                    event.getTextChannel().deleteMessages(withoutPinnedMessages).queue();
                    new ReturnMessage(event.getTextChannel(), event.getAuthor().getName() + " hat " + clearNumb + " Nachrichten gelöscht!", Color.RED, 5).build();
                    KetchupLogger.log(KetchupLogger.LogType.WARNING, event.getAuthor().getName() + " removed " + messages.size() + " messages in Channel #" + event.getTextChannel().getName());
                } catch (Exception ignored){}
            }
        }
    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "clear");
    }

    @Override
    public String help() {
        return "clear lines by amount -> .clear <lines>";
    }
}
