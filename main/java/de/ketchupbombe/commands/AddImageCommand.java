package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.ReturnMessage;
import de.ketchupbombe.util.RoleManager;
import de.ketchupbombe.util.images.ImageCategory;
import de.ketchupbombe.util.images.ImageManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class AddImageCommand extends Command {
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
        if (args.length == 2) {
            ImageCategory category = ImageCategory.getCategoryByCmd(args[0]);
            String url = args[1];
            ImageManager.registerNewImage(category, url);
            String msg = event.getAuthor().getName() + " hat das Bild \'" + url + "\' zu der Kategorie \'" + category.getPath() + "\' hinzugefügt!";
            new ReturnMessage(event.getTextChannel(), msg, Color.ORANGE, 5).build();
        }
    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "addimage");
    }

    @Override
    public String help() {
        return "Add new images. Usage: .addimage <category> <url>";
    }
}
