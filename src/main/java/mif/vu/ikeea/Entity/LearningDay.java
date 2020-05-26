package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter
public class LearningDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "topic_learning_day",
            joinColumns = {@JoinColumn(name = "learning_day_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")})
    private List<Topic> topics = new ArrayList<>();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user_id", nullable = false)
    private ApplicationUser user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "learningDay")
    private Set<Comment> comments = new HashSet<>();
}
