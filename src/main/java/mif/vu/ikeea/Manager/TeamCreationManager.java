package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Repository.TeamRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Factory.TeamFactory;
import mif.vu.ikeea.Service.TeamService;
import mif.vu.ikeea.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

public class TeamCreationManager {

    private TeamService teamService;

    public TeamCreationManager(TeamService teamService){
        this.teamService = teamService;
    }

    public Team getTeam(long id){
        return teamService.findTeamById(id);
    }

}
