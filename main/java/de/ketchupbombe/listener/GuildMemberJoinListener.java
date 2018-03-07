package de.ketchupbombe.listener;

import de.ketchupbombe.Main;
import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.KetchupLogger;
import de.ketchupbombe.util.Role;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class GuildMemberJoinListener extends ListenerAdapter {
    public GuildMemberJoinListener() {
        this.messages.add("%name% izz da! :sunglasses: ");
        this.messages.add("Was will denn %name% hier :rolling_eyes: ");
        this.messages.add("Wer hat %name% eingeladen...");
        this.messages.add("%name% tritt der Party bei :call_me: ");
        this.messages.add(":heart:-lich Willkommen %name%");
        this.messages.add("PlayMinity is love, PlayMinity is life :heart: Danke Danke %name%");
        this.messages.add("Hey! %name%");
    }

    private ArrayList<String> messages = new ArrayList<>();

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        if (event.getMember().getRoles().isEmpty()) {
            GuildController controller = new GuildController(event.getGuild());
            controller.addRolesToMember(event.getMember(), Main.jda.getRoleById(Role.USER.getId())).queue();
            Main.jda.getTextChannelById(Constants.TEXT_LOBBY_ID).sendMessage(getRandomJoinMessage(event.getUser())).queue();
            KetchupLogger.log(KetchupLogger.LogType.INFO, "New Member: \'" + event.getUser().getName() + "\'");
        }
    }

    private MessageEmbed getRandomJoinMessage(User user) {
        Random random = new Random();
        String message = this.messages.get(random.nextInt(messages.size())).replaceAll("%name%", user.getAsMention());
        return new EmbedBuilder().setDescription(message).setColor(Color.ORANGE).build();
    }

}
