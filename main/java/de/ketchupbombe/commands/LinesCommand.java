package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.*;
import de.ketchupbombe.util.images.ImageCategory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class LinesCommand extends Command {

    private int lines = -1;

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
        if (args.length == 1) {
            ImageCategory category = ImageCategory.getCategoryByCmd(args[0]);
            if (category != null) {
                String path = "images" + System.getProperty("file.separator") + category.getPath() + ".txt";
                FileManager fileManager = new FileManager(path);
                String msg = "Die Kategorie " + category.getCmd() + " hat derzeit " + (fileManager.getLines() - 1) + " Einträge.";
                KetchupLogger.log(KetchupLogger.LogType.INFO, category.getCmd() + " has " + (fileManager.getLines() - 1) + " entrys.");
                new ReturnMessage(event.getTextChannel(), msg, Color.BLUE, -1, event.getAuthor().getName()).build();

            } else {
                new ReturnMessage(event.getTextChannel(), "Die Kategorie " + args[0] + " gibt es nicht! (.imagecategories)", Color.RED, 3).build();
            }
        }
    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "lines");
    }
}
