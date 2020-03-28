package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.Role;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Service.UserServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class UserCreationManager
{
    @Autowired
    private UserServiceInterface userServiceInterface;

    public String create(String email, String first_name, String last_name, String role, String password, long manager_id, Team team){
        Role userRole = Role.valueOf(role);
        User manager = userServiceInterface.find(manager_id);
        User user = UserFactory.createUser(email,first_name,last_name,userRole, password, manager, team);
        userServiceInterface.add(user);
        return "200";
    }

    public String delete(long id){
        userServiceInterface.delete(id);
        return "200";
    }

    public User get(long id){
        return userServiceInterface.find(id);
    }

    public String update(User user){
        userServiceInterface.update(user);
        return "200";
    }

    public List<User> getAll(){
        return userServiceInterface.getAll();
    }
}
