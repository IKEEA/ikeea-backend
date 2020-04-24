package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmail(String email);

    Optional<ApplicationUser> findByEmailAndEnabled(String email, Boolean enabled);

    Optional<ApplicationUser> findByToken(String token);

    Boolean existsByEmail(String email);
}
