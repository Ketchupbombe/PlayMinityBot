package de.ketchupbombe.listener;

import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.ReturnMessage;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.DisconnectEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class DisconnectListener extends ListenerAdapter {

    @Override
    public void onDisconnect(DisconnectEvent event) {
        super.onDisconnect(event);
        Guild guild = event.getJDA().getGuildById(Constants.GUILD_ID);
        new ReturnMessage(guild.getTextChannelById(Constants.TEXT_LOBBY_ID), "PlayMinity-Bot disconnected!", Color.RED, -1)
                .build();
        KetchupLogger.log(KetchupLogger.LogType.INFO, "PlayMinity-Bot disconnected");
    }
}
