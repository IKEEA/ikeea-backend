package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.Repository.TeamRepository;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public Team add(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Transactional
    public void update(Team team) {
        teamRepository.save(team);
    }

    @Transactional
    public Team loadById(Long id) throws ResourceNotFoundException {
        Team team = teamRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team was not found")
                );

        return team;
    }

    @Transactional
    public Team loadByManagerId(Long id) throws ResourceNotFoundException {
        Team team = teamRepository.findTeamByManagerId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team was not found")
                );

        return team;
    }
}
