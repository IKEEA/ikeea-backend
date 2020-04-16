package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="learning_day_id", nullable = false)
    private LearningDay learningDay;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private ApplicationUser user;
}
