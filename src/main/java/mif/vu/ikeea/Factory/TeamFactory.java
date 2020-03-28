package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;

public class TeamFactory {

    public static void createTeam(String description, String title, User manager){
        Team team = new Team();
        team.setDescription(description);
        team.setTitle(title);
        team.setManager(manager);
    }
}
