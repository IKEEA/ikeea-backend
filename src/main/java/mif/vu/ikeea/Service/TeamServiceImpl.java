package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Repository.TeamRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams(){
        Iterable<Team> source = teamRepository.findAll();
        List<Team> teams = new ArrayList<>();
        source.forEach(teams::add);
        return teams;
    }

    public void addTeam(Team team){
        teamRepository.save(team);
    }

    public void deleteTeamById(long id){
        teamRepository.deleteById(id);
    }

    public void updateTeam(long id, Team team){
        teamRepository.save(team);
    }

    public Team findTeamById(long id){
        return teamRepository.findById(id).get();
    }
}
