package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Comment;
import mif.vu.ikeea.Entity.LearningDay;

import java.util.Date;

public class CommentFactory {
    public static Comment createComment(String text, Date date, LearningDay learningDay, ApplicationUser user){
        Comment comment = new Comment();
        comment.setComment(text);
        comment.setDate(date);
        comment.setLearningDay(learningDay);
        comment.setUser(user);

        return comment;
    }
}
