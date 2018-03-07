package de.ketchupbombe.commands.core;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public abstract class Command {

    private CommandManager.CommandContainer container;

    public ArrayList<Long> forbiddenChannels = new ArrayList<>();
    public ArrayList<String> aliases = new ArrayList<>();

    private int permissionValue = 0;

    public abstract boolean called(String[] args, MessageReceivedEvent event);

    public abstract void onCommand(String[] args, MessageReceivedEvent event);

    public abstract void executed(boolean successed, MessageReceivedEvent event);

    public String help() {
        return "";
    }

    public void setContainer(CommandManager.CommandContainer container) {
        this.container = container;
    }

    public void setPermissionValue(int permissionValue) {
        this.permissionValue = permissionValue;
    }

    public CommandManager.CommandContainer getContainer() {
        return container;
    }

    public int getPermissionValue() {
        return permissionValue;
    }
}
