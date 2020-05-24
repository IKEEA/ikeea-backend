package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ManyToMany(mappedBy = "learningDays")
    private List<Topic> topics = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private ApplicationUser user;

    @OneToMany(mappedBy = "learningDay", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new LinkedHashSet<>();
}
