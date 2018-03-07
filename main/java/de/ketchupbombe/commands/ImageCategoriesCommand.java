package de.ketchupbombe.commands;

import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.RoleManager;
import de.ketchupbombe.util.images.ImageCategory;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class ImageCategoriesCommand extends Command {
    public ImageCategoriesCommand() {
        this.aliases.add("icategories");
    }

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
        MessageBuilder builder = new MessageBuilder();
        for (ImageCategory category : ImageCategory.values()) {
            builder.append("-> .").append(category.getCmd()).append(System.getProperty("line.separator"));
        }
        event.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(builder.build()).queue());
    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "imagecategories");
    }
}
