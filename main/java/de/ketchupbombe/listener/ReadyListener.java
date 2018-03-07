package de.ketchupbombe.listener;

import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.ReturnMessage;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        super.onReady(event);
        Guild guild = event.getJDA().getGuildById(Constants.GUILD_ID);
        String msg = "Connected!" + System.getProperty("line.separator") + "Current version: " + Constants.VERSION;
        new ReturnMessage(guild.getTextChannelById(Constants.TEXT_LOBBY_ID), msg, Color.GREEN, -1, "PlayMinity-Bot").build();
        KetchupLogger.log(KetchupLogger.LogType.INFO, "PlayMinity-Bot connected {Current version: " + Constants.VERSION + "}");

    }
}
