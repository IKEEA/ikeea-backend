package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);

    Boolean existsByEmail(String email);
}
