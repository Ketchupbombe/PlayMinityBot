package de.ketchupbombe.util;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class ReturnMessage {

    private final TextChannel channel;
    private final String message;
    private final Color color;
    private final int alive;
    private final String author;

    public ReturnMessage(TextChannel channel, String message, Color color, int alive, String author) {
        this.channel = channel;
        this.message = message;
        this.color = color;
        this.alive = alive;
        this.author = author;
    }

    public ReturnMessage(TextChannel channel, String message, Color color, int alive) {
        this(channel, message, color, alive, null);
    }

    public void build() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription(this.message);
        builder.setColor(this.color);
        if (author != null)
            builder.setAuthor(this.author);
        Message msg = this.channel.sendMessage(builder.build()).complete();

        if (this.alive == -1) {
            return;
        }

        new Timer("ReturnMessageThread").schedule(new TimerTask() {
            @Override
            public void run() {
                msg.delete().queue();
            }
        }, this.alive * 1000);


    }
}
