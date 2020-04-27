package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Comment;

import java.util.Date;

@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String comment;
    private Date date;
    private Long learningDayId;
    private Long userId;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.date = comment.getDate();
        this.learningDayId = comment.getLearningDay().getId();
        this.userId = comment.getUser().getId();
    }
}
