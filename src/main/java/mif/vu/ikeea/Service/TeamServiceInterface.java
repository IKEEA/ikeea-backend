package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Team;

import java.util.List;

public interface TeamServiceInterface {

    void add(Team team);

    void update(Team team);

    void delete(Long id);

    List<Team> getAll();

    Team findOneById(Long id);
}
