package de.ketchupbombe.listener;

import de.ketchupbombe.commands.core.CommandManager;
import de.ketchupbombe.util.Constants;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class MessageReceifedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        String raw = event.getMessage().getContentRaw();
        if (raw.startsWith(Constants.PREFIX)) {
            CommandManager.handleCommand(CommandManager.parseToContainer(raw, event));
        }
    }
}
