package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "topic_learningday",
                joinColumns = {@JoinColumn(name = "topic_id")},
                inverseJoinColumns = {@JoinColumn(name = "learning_day_id")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"topic_id", "learning_day_id"})})
    private List<LearningDay> learningDays = new ArrayList<>();
}
