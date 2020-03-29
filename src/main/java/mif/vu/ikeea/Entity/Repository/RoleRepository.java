package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
