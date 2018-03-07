package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.FileManager;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.RoleManager;
import de.ketchupbombe.util.images.ImageCategory;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class ImageCommand extends Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (RoleManager.isAllowedForCommand(this, event.getMember()))
            return true;

        String command = this.getContainer().getCommand();
        ImageCategory category = ImageCategory.getCategoryByCmd(command);
        assert category != null;
        if (category.isNsfw() && !event.getTextChannel().isNSFW()) return true;

        this.forbiddenChannels.add(Constants.TEXT_INFO_ID);
        return false;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {
        String command = this.getContainer().getCommand();
        ImageCategory category = ImageCategory.getCategoryByCmd(command);
        assert category != null;
        if (!(category.isNsfw()) && event.getTextChannel().getIdLong() != Constants.TEXT_NSFW_ID) return;
        FileManager fileManager = new FileManager("images" + System.getProperty("file.separator") + category.getPath() + ".txt");
        //Checking lines
        if (fileManager.getLines() < category.getAmount()) {
            KetchupLogger.log(KetchupLogger.LogType.WARNING, "Not enough images found in Category \'" + category.getPath() + "\'!");
            return;
        }
        try {
            MessageBuilder builder = new MessageBuilder();
            for (int i = 0; i < category.getAmount(); i++) {
                builder.append(fileManager.getRandomLine() + System.getProperty("line.separator") + System.getProperty("line.separator"));
            }
            event.getTextChannel().sendMessage(builder.build()).queue();
        } catch (IOException ignored) {
        }

    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        String command = this.getContainer().getCommand();
        KetchupLogger.logCommand(successed, event, command);
    }
}
