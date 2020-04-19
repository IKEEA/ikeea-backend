package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Repository.TeamRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Responses.UserManagerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/manager")
public class ManagerController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping(path = "/{id}/users")
    public @ResponseBody
    List<UserManagerResponse> list(@PathVariable Long id) {

        //TODO add check if this user has manager role

        Optional<ApplicationUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Empty User");
        }
        ApplicationUser manager = optionalUser.get();
        Team managerTeam = teamRepository.findTeamByManagerId(manager.getId());

        Iterable<ApplicationUser> users = managerTeam.getUsers();
        List<UserManagerResponse> userManagerResponses = new ArrayList<>();

        for (ApplicationUser applicationUser : users) {
            userManagerResponses.add(new UserManagerResponse(applicationUser));
        }

        return userManagerResponses;
    }
}
