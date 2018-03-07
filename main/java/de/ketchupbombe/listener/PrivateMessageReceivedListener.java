package de.ketchupbombe.listener;

import de.ketchupbombe.util.KetchupLogger;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class PrivateMessageReceivedListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        super.onPrivateMessageReceived(event);
        if (!event.getAuthor().isBot()) {
            KetchupLogger.log(KetchupLogger.LogType.INFO, event.getAuthor().getName() + "->" + event.getJDA().getSelfUser().getName() + ": " + event.getMessage().getContentDisplay());
        }
    }
}
