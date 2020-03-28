package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Manager.UserCreationManager;
import mif.vu.ikeea.Service.TeamService;
import mif.vu.ikeea.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserCreationManager userCreationManager;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<User> add(@RequestParam String first_name, @RequestParam String last_name,  @RequestParam String role, @RequestParam String email, @RequestParam String password, @RequestParam Long manager_id, @RequestParam Long team_id){
        if (email == null || password == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Team team = teamService.findOneById(team_id);
        User user = userCreationManager.create(email, first_name, last_name, role, password, manager_id, team);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public @ResponseBody List<User> list(){
        return userService.getAll();
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody void delete(@PathVariable Long id) {
        userService.delete(id);
    }


    @PutMapping(path = "/update/{id}")
    public @ResponseBody User updateEmail(@PathVariable Long id, @RequestParam String email){
        User user = userService.findOneById(id);
        user.setEmail(email);
        userService.update(user);

        return user;
    }
}
