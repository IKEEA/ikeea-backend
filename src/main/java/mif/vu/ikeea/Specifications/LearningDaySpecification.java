package mif.vu.ikeea.Specifications;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

public class LearningDaySpecification {

    public static Specification<LearningDay> withDate(Date date) {
        if (date == null) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.get("date"), date);
    }

    public static Specification<LearningDay> withTopic(Long topicId) {
        if (topicId == null) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.join("topics", JoinType.LEFT).get("id"), topicId);
    }

    public static Specification<LearningDay> withUser(Long userId) {
        if (userId == null) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.join("user", JoinType.LEFT).get("id"), userId);
    }

    public static Specification<LearningDay> withManager(Long managerId) {
        if (managerId == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.join("user", JoinType.LEFT).get("manager").get("id"), managerId);
    }
}
