package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.LearningDay;
import org.springframework.data.repository.CrudRepository;

public interface GoalRepository extends CrudRepository<Goal, Long> {
    Iterable<Goal> findAllByUserId(Long userId);
}
