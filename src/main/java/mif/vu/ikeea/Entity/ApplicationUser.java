package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Enums.ERole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter @Setter
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "restriciton_days", columnDefinition = "integer default 3")
    private Integer restrictionDays = 3;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private ApplicationUser manager;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<LearningDay> learningDays = new ArrayList<>();

    @OneToMany(mappedBy="manager", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<ApplicationUser> children = new ArrayList<>();

    public List<ERole> getRoleNames() {
        List<ERole> roleNames = new ArrayList<>();

        for (Role role : roles) {
            roleNames.add(role.getName());
        }

        return roleNames;
    }

    public void setManagerWithChild(ApplicationUser manager) {
        this.manager = manager;
        manager.getChildren().add(this);
    }
}
