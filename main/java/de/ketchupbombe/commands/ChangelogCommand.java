package de.ketchupbombe.commands;

import de.ketchupbombe.Main;
import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.RoleManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class ChangelogCommand extends Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (RoleManager.isAllowedForCommand(this, event.getMember()))
            return true;
        return false;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {
        if (args.length == 0) {
            EmbedBuilder builder = new EmbedBuilder();
            StringBuilder sBuilder = new StringBuilder();
            try {
                for (String s : Main.CHANGELOG_FILE.readLines()) {
                    sBuilder.append(s).append(System.getProperty("line.separator"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            builder.setColor(Color.ORANGE);
            builder.setDescription(sBuilder.toString());
            event.getTextChannel().sendMessage(builder.build()).queue();
        }
    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "changelog");
    }

    @Override
    public String help() {
        return "see all changes from this bot!";
    }
}
