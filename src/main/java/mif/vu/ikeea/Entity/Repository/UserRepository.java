package mif.vu.ikeea.Entity.Repository;

import mif.vu.ikeea.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
