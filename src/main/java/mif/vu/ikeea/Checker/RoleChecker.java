package mif.vu.ikeea.Checker;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.RepositoryService.RoleService;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleChecker {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void checkToPromote(ApplicationUser user) {
        for (Role role : user.getRoles()) {
            if (role.getName().equals(ERole.LEADER)) {
                continue;
            }
            roleService.changeRole(user, ERole.LEADER);
        }
    }

    public void checkToDemote(ApplicationUser user) {
        List<ApplicationUser> applicationUsers = userService.getAllByManagerId(user.getId());
        if(!applicationUsers.isEmpty()) { return; }
        roleService.changeRole(user, ERole.DEVELOPER);
    }
}
