package mif.vu.ikeea.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "token")
    private String token;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "team_id", columnDefinition="integer")
    private Team team;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User manager;

    @ManyToMany
    @JoinTable(name = "restriction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restriction_id"))
    private List<Restriction> restrictions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<LearningDay> learningDays = new ArrayList<>();
}
