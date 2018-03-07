package de.ketchupbombe.util;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public enum Role {

    ADMIN("Admin", 100, 414673491858685963L),
    BOT("Bot", 99, 414033769842999307L),
    MODERATOR("Moderator", 75, 414002183281836034L),
    FRIEND("Friend", 25, 414676169980379138L),
    WELL_KNOWN("Well-Known", 20, 414675339281694721L),
    EX_STAFF("(ex)-Teammitglied", 15, 414001742049574924L),
    USER("User", 10, 414001222157205504L),
    MUTED("muted", 0, 415109314559213569L);

    private String rolename;
    private int permLevel;
    private long id;

    Role(String rolename, int permLevel, long id) {
        this.rolename = rolename;
        this.permLevel = permLevel;
        this.id = id;
    }

    public int getPermLevel() {
        return permLevel;
    }

    public String getRolename() {
        return rolename;
    }

    public long getId() {
        return id;
    }

    public static Role getRoleByName(String rolename) {
        switch (rolename) {
            case "Bot":
                return BOT;
            case "Admin":
                return ADMIN;
            case "Moderator":
                return MODERATOR;
            case "Friend":
                return FRIEND;
            case "Well-Known":
                return WELL_KNOWN;
            case "(ex)-Teammitglied":
                return EX_STAFF;
            case "muted":
                return MUTED;

            default:
                return USER;
        }

    }
}
