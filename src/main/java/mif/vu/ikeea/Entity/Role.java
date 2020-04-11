package mif.vu.ikeea.Entity;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Enums.ERole;
import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}