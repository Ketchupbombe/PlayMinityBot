package de.ketchupbombe.commands;

import de.ketchupbombe.Main;
import de.ketchupbombe.commands.core.Command;
import de.ketchupbombe.util.*;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

import java.awt.*;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class UnmuteCommand extends Command {
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
            Member toUnMute = null;
            try {
                toUnMute = event.getMessage().getMentionedMembers().get(0);
            } catch (Exception ignored) {
            }

            if (toUnMute != null) {
                GuildController controller = new GuildController(event.getGuild());
                controller.removeRolesFromMember(toUnMute, Main.jda.getRoleById(Role.MUTED.getId())).queue();
                String msg = event.getAuthor().getName() + " unmuted " + toUnMute.getUser().getName();
                new ReturnMessage(event.getTextChannel(), msg, Color.GREEN, -1).build();
                KetchupLogger.log(KetchupLogger.LogType.INFO , event.getAuthor().getName() + " unmuted " + toUnMute.getUser().getName());
            }

        }
    }

    @Override
    public void executed(boolean successed, MessageReceivedEvent event) {
        KetchupLogger.logCommand(successed, event, "unmute");
    }
}
