package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Team;

import java.util.List;

public interface TeamServiceInterface {

    void add(Team team);
    void delete(long id);
    List<Team> getAll();
    void update(Team team);
    Team find(long id);
}
