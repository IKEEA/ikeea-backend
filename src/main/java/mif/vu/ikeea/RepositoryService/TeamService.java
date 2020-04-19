package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.Repository.TeamRepository;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public Team getTeamByManager(Long managerId) {
        Team team = teamRepository.findTeamByManagerId(managerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Goal was not found")
                );

        return team;
    }
}
