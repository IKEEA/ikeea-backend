package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.Goal;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface GoalRepository extends CrudRepository<Goal, Long>, JpaSpecificationExecutor<Goal> {
    Iterable<Goal> findAllByUserId(Long userId);
}
