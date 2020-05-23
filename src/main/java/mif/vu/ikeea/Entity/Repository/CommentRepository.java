package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.Comment;
import mif.vu.ikeea.Entity.Goal;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByLearningDayId(Long learningDayId);
    Iterable<Comment> findAllByUserId(Long userId);
}
