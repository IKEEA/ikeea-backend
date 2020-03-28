package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;

import java.util.List;

public interface UserServiceInterface {
    void add(User user);

    void delete(Long id);

    List<User> getAll();

    void update(User user);

    User findOneById(Long id);
}
