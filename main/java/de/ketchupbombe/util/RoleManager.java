package de.ketchupbombe.util;

import de.ketchupbombe.commands.core.Command;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class RoleManager {

    public static boolean isAllowedForCommand(Command command, Member member) {
        if (command.getPermissionValue() >= getHighestPermissionValueByMember(member))
            return true;
        return false;
    }

    public static int getHighestPermissionValueByMember(Member member) {
        if (member.getRoles().isEmpty()) return 0;
        Role highestRole = member.getRoles().get(0);
        de.ketchupbombe.util.Role role = de.ketchupbombe.util.Role.getRoleByName(highestRole.getName());

        return role.getPermLevel();

    }

}
