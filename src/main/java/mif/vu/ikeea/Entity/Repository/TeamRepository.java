package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<Team> findTeamByManagerId(Long id);
}
