package de.ketchupbombe;

import de.ketchupbombe.commands.*;
import de.ketchupbombe.commands.core.CommandManager;
import de.ketchupbombe.listener.*;
import de.ketchupbombe.util.Constants;
import de.ketchupbombe.util.FileManager;
import de.ketchupbombe.util.images.ImageCategory;
import de.ketchupbombe.util.images.ImageManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class Main {

    public static FileManager LOG_FILE;
    public static FileManager CHANGELOG_FILE;
    public static JDA jda;

    public static void main(String[] args) throws LoginException {

        LOG_FILE = new FileManager("log.log");
        CHANGELOG_FILE = new FileManager("changelog.txt");
        ImageManager.init();

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(Constants.BOT_TOKEN);
        builder.setAutoReconnect(true);
        builder.setGame(Game.of(Game.GameType.DEFAULT, "V." + Constants.VERSION));
        init(builder);

        jda = builder.buildAsync();

    }

    private static void init(JDABuilder builder) {
        builder.addEventListener(new ReadyListener());
        builder.addEventListener(new PrivateMessageReceivedListener());
        builder.addEventListener(new DisconnectListener());
        builder.addEventListener(new MessageReceifedListener());
        builder.addEventListener(new GuildMemberJoinListener());
        builder.addEventListener(new ResumeListener());

        CommandManager.registerCommand("ping", new PingCommand(), 0);
        CommandManager.registerCommand("help", new HelpCommand(), 0);
        CommandManager.registerCommand("addimage", new AddImageCommand(), 20);
        CommandManager.registerCommand("imagecategories", new ImageCategoriesCommand(), 20);
        CommandManager.registerCommand("clear", new ClearCommand(), 75);
        CommandManager.registerCommand("mute", new MuteCommand(), 75);
        CommandManager.registerCommand("unmute", new UnmuteCommand(), 75);
        CommandManager.registerCommand("lines", new LinesCommand(), 50);
        CommandManager.registerCommand("changelog", new ChangelogCommand(), 0);
        CommandManager.registerCommand("developer", new DeveloperCommand(), 75);

        //This is for ex. for ".hentai"
        for (ImageCategory category : ImageCategory.values()) {
            CommandManager.registerCommand(category.getCmd(), new ImageCommand(), 0);
        }

    }
}
