package mif.vu.ikeea.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<ApplicationUser> users = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="manager_id", columnDefinition="bigint")
    private ApplicationUser manager;
}
