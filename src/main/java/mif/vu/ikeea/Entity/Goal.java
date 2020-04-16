package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter @Setter
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;


    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private ApplicationUser user;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable = false)
    private Topic topic;
}
