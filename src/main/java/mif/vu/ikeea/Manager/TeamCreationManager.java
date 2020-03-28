package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Factory.TeamFactory;
import mif.vu.ikeea.Service.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamCreationManager {

    @Autowired
    private TeamServiceInterface teamServiceInterface;

    public Team get(long id){
        return teamServiceInterface.find(id);
    }

    public List<Team> getAll(){
        return teamServiceInterface.getAll();
    }

    public String create(String description, String title, User manager){

        Team team = TeamFactory.createTeam(description,title,manager);
        teamServiceInterface.add(team);
        return "200";
    }

    public String delete(long id){
        teamServiceInterface.delete(id);
        return "200";
    }

    public String update(Team team){
        teamServiceInterface.update(team);
        return "200";
    }
}
