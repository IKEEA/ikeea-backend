package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Service.UserService;
import mif.vu.ikeea.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class TestController {

    @Autowired
    private UserService userService;

    /*
        @GetMapping(path = "/all")
        public @ResponseBody Iterable<User> getAllUsers () {
            return userRepository.findAll();

        }
        */

    @PostMapping(path = "/addUser")
    public @ResponseBody String addAnotherUser (@RequestParam String name, @RequestParam String email, @RequestParam String pssw){
        User n = new User();
        n.setEmail(email);
        n.setPassword(pssw);

        Team team = new Team();
        team.setTitle(name);

        userService.addUser(n);
        return "Saved";
    }

    @GetMapping(path = "/getUsers")
    public @ResponseBody List<User> getAllUsers (){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/deleteUser")
    public @ResponseBody String deleteUser (@RequestParam Long id){
        userService.deleteUserById(id);
        return "User deleted";
    }
}
