package mif.vu.ikeea.Service;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        Iterable<User> source = userRepository.findAll();
        List<User> users = new ArrayList<>();
        source.forEach(users::add);
        return users;
    }

    public void add(User user){
        userRepository.save(user);
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public User find(long id){
        return userRepository.findById(id).get();
    }
}
