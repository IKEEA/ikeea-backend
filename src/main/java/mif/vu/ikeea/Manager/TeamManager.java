package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TeamManager {

    @Autowired
    private UserService userService;

    private final Set<Long> userIds = new HashSet<>();

    public void updateRestrictionDay(Team team, Integer restrictionDays) {
        List<ApplicationUser> users = team.getUsers();
        Set<Long> userIds = collectUserIds(users);
        userService.updateRestrictionDays(restrictionDays, userIds);
    }

    private Set<Long> collectUserIds(List<ApplicationUser> userList) {
        for (ApplicationUser user : userList) {
            userIds.add(user.getId());
            collectUserIds(user.getChildren());
        }

        return userIds;
    }
}
