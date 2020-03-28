package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Factory.TeamFactory;
import mif.vu.ikeea.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamManager {

    @Autowired
    private TeamService teamService;

    public Team create(String description, String title, User manager){
        Team team = TeamFactory.createTeam(description,title,manager);
        teamService.add(team);

        return team;
    }
}
