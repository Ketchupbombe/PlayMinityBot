package de.ketchupbombe.util.images;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public enum ImageCategory {

    //TODO: manage via File-System

    BOOBS("boobs", 1, "boobs", true),
    ASS("ass", 1, "ass", true),
    HENTAI("hentai", 1, "hentai", true),
    HENTAI_BOMB("hentaibomb", 4, "hentai", true),
    PLAYMINITY("playminity", 1, "playminity", false),
    CELINE("celine", 1, "celine", true, "Cxline", "FrxnUnicxrn", "playminityhure");

    String cmd;
    int amount;
    String path;
    boolean nsfw;
    private String[] alias;

    ImageCategory(String cmd, int amount, String path, boolean nsfw, String... alias) {
        this.cmd = cmd;
        this.amount = amount;
        this.path = path;
        this.nsfw = nsfw;
        this.alias = alias;
    }

    public static ImageCategory getCategoryByCmd(String cmd) {
        if (cmd.equalsIgnoreCase("boobs"))
            return BOOBS;
        else if (cmd.equalsIgnoreCase("ass"))
            return ASS;
        else if (cmd.equalsIgnoreCase("hentai"))
            return HENTAI;
        else if (cmd.equalsIgnoreCase("hentaibomb"))
            return HENTAI_BOMB;
        else if (cmd.equalsIgnoreCase("playminity"))
            return PLAYMINITY;
        else if (cmd.equalsIgnoreCase("celine") || cmd.equalsIgnoreCase("cxline") || cmd.equalsIgnoreCase("frxnUnicxrn"))
            return CELINE;

        else return null;
    }

    public boolean hasAliases() {
        return this.getAlias().length != 0;
    }

    public String getCmd() {
        return cmd;
    }

    public int getAmount() {
        return amount;
    }

    public String getPath() {
        return path;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public String[] getAlias() {
        return alias;
    }
}
