package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Repository.TeamRepository;
import mif.vu.ikeea.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService implements TeamServiceInterface {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAll(){
        Iterable<Team> teamIterable = teamRepository.findAll();
        List<Team> teams = new ArrayList<>();
        teamIterable.forEach(teams::add);

        return teams;
    }

    public void add(Team team){
        teamRepository.save(team);
    }

    public void delete(Long id){
        teamRepository.deleteById(id);
    }

    public void update(Team team){
        teamRepository.save(team);
    }

    public Team findOneById(Long id){
        return teamRepository.findById(id).get();
    }
}
