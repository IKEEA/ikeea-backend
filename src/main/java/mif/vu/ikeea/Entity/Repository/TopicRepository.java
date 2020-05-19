package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Long>{
    Iterable<Topic> findAllByLearningDays(LearningDay learningDays);

    Boolean existsByTitle(String title);
}
