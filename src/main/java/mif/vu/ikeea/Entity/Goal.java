package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty", nullable = false)
    @Size(max = 3)
    private Integer difficulty;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private ApplicationUser user;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable = false)
    private Topic topic;
}
