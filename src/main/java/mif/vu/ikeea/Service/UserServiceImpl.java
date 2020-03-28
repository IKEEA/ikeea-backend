package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        Iterable<User> source = userRepository.findAll();
        List<User> users = new ArrayList<>();
        source.forEach(users::add);
        return users;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    public void updateUser(long id, User user){
        userRepository.save(user);
    }

    public Optional<User> getUser(long id){
        return userRepository.findById(id);
    }

    public User findUserById(long id){
        return userRepository.findById(id).get();
    }
}
