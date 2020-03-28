package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Team;

import java.util.List;

public interface TeamService {

    void addTeam(Team team);
    void deleteTeamById(long id);
    List<Team> getAllTeams();
    void updateTeam(long id, Team team);
    Team findTeamById(long id);
}
