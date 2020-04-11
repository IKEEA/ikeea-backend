package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.ApplicationUser;

public class TeamFactory {

    public static Team createTeam(String description, String title, ApplicationUser manager){
        Team team = new Team();

        team.setDescription(description);
        team.setTitle(title);
        team.setManager(manager);

        return team;
    }
}
