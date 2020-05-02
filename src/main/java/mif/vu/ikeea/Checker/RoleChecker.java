package mif.vu.ikeea.Checker;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Repository.RoleRepository;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleChecker {
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    public void checkToPromote(ApplicationUser user) {
        for (Role role : user.getRoles()) {
            if (role.getName().equals(ERole.LEADER)) {
                continue;
            }
            user.getRoles().remove(role);
            Set<Role> roles = new HashSet<>();
            Role role1 = roleRepository.findByName(ERole.LEADER)
                    .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));
            roles.add(role1);

            user.setRoles(roles);
            userService.update(user);
        }
    }

    public void checkToDemote(ApplicationUser user) {
        List<ApplicationUser> applicationUsers = userService.getAllByManagerId(user.getId());

        if(!applicationUsers.isEmpty()) { return; }
        user.getRoles().removeAll(user.getRoles());
        Set<Role> roles = new HashSet<>();
        Role role1 = roleRepository.findByName(ERole.DEVELOPER)
                .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));
        roles.add(role1);

        user.setRoles(roles);
        userService.update(user);
    }
}
