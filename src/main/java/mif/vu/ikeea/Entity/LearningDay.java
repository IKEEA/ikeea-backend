package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "topic_learning_day",
            joinColumns = {@JoinColumn(name = "learning_day_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")})
    private List<Topic> topics = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private ApplicationUser user;

    @OneToMany(mappedBy = "learningDay", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new LinkedHashSet<>();
}
