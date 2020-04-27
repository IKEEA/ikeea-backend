package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.*;
import mif.vu.ikeea.Factory.CommentFactory;
import mif.vu.ikeea.Payload.CommentRequest;
import mif.vu.ikeea.Payload.UpdateCommentRequest;
import mif.vu.ikeea.RepositoryService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class CommentManager {

    @Autowired
    private UserService userService;

    @Autowired
    private LearningDayService learningDayService;

    @Autowired
    private CommentService commentService;

    @Transactional
    public Comment create(CommentRequest commentRequest) {
        LearningDay learningDay = learningDayService.loadById(commentRequest.getLearningDayId());
        ApplicationUser user = userService.loadById(commentRequest.getUserId());

        Comment comment = CommentFactory.createComment(
                commentRequest.getComment(),
                new Date(),
                learningDay,
                user
        );

        Comment result = commentService.add(comment);

        return result;
    }

    @Transactional
    public void update(Comment comment, UpdateCommentRequest updateCommentRequest) {
        if (updateCommentRequest.getComment() != null) {
            comment.setComment(updateCommentRequest.getComment());
        }

        if (updateCommentRequest.getDate() != null) {
            comment.setDate(updateCommentRequest.getDate());
        }

        if (updateCommentRequest.getLearningDayId() != null) {
            LearningDay learningDay = learningDayService.loadById(updateCommentRequest.getLearningDayId());
            comment.setLearningDay(learningDay);
        }

        if (updateCommentRequest.getUserId() != null) {
            ApplicationUser user = userService.loadById(updateCommentRequest.getUserId());
            comment.setUser(user);
        }

        commentService.update(comment);
    }
}
