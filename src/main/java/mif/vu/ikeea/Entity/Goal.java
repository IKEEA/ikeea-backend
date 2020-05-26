package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Enums.EGoalStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EGoalStatus status;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user_id", nullable = false)
    private ApplicationUser user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="topic_id", nullable = false)
    private Topic topic;

//    @Version
//    @Column(name = "OPT_LOCK_VERSION")
//    private Integer version;
}
