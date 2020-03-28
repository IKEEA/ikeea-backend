package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    void deleteUserById(Long id);
    List<User> getAllUsers();
    void updateUser(String id, User user);
    Optional<User> getUser(Long id);
}
