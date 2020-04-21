package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.ApplicationUser;

public class TeamFactory {
    public static Team createTeam(String title, ApplicationUser manager){
        Team team = new Team();
        team.setTitle(title);
        team.setManager(manager);

        return team;
    }
}
