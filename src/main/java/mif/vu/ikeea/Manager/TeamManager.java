package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Factory.TeamFactory;
import mif.vu.ikeea.Payload.TeamRequest;
import mif.vu.ikeea.Payload.UpdateTeamRequest;
import mif.vu.ikeea.RepositoryService.TeamService;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TeamManager {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Transactional
    public Team create(TeamRequest teamRequest) {
        ApplicationUser manager = userService.loadById(teamRequest.getManagerId());
        Team team = TeamFactory.createTeam(
                teamRequest.getTitle(),
                manager
        );

        Team result = teamService.add(team);

        return result;
    }

    @Transactional
    public void update(Team team, UpdateTeamRequest updateTeamRequest) {
        if (updateTeamRequest.getTitle() != null) {
            team.setTitle(updateTeamRequest.getTitle());
        }

        if (updateTeamRequest.getManagerId() != null) {
            ApplicationUser manager = userService.loadById(updateTeamRequest.getManagerId());
            team.setManager(manager);
        }

        teamService.update(team);
    }

    private final Set<Long> userIds = new HashSet<>();

    public void updateRestrictionDay(List<ApplicationUser> users, Integer restrictionDays) {
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
