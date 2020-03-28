package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUserById(long id);
    List<User> getAllUsers();
    void updateUser(long id, User user);
    User findUserById(long id);
}
