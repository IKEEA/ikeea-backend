package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LearningDayRepository extends CrudRepository<LearningDay, Long>, JpaSpecificationExecutor<LearningDay> {
    Iterable<LearningDay> findAllByUserId(Long userId);
    Iterable<LearningDay> findAllByUserIdAndDate(Long userId, Date date);
}
