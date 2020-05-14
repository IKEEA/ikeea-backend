package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.LearningDay;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface LearningDayRepository extends CrudRepository<LearningDay, Long>, JpaSpecificationExecutor<LearningDay> {
    Iterable<LearningDay> findAllByUserId(Long userId);
}
