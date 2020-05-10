package mif.vu.ikeea.Specifications;

import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.LearningDay;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.Date;

public class GoalSpecification {

    public static Specification<Goal> withTopic(Long topicId) {
        if (topicId == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.join("topic", JoinType.LEFT).get("id"), topicId);
        }
    }

    public static Specification<Goal> withUser(Long userId) {
        if (userId == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.join("user", JoinType.LEFT).get("id"), userId);
        }
    }

    public static Specification<Goal> withManager(Long managerId) {
        if (managerId == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.join("user", JoinType.LEFT).get("manager").get("id"), managerId);
        }
    }
}
