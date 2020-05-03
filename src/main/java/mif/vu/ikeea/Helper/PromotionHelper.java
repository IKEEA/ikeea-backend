package mif.vu.ikeea.Helper;

import mif.vu.ikeea.Checker.RoleChecker;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Repository.RoleRepository;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import mif.vu.ikeea.Payload.UpdateForLeaderRequest;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class PromotionHelper {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleChecker roleChecker;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void updateRoles(ApplicationUser user, UpdateForLeaderRequest updateForLeaderRequest) {
        ApplicationUser previousManager = user.getManager();
        ApplicationUser manager = userService.loadById(updateForLeaderRequest.getManagerId());
        roleChecker.checkToPromote(manager);
        user.setManager(manager);
        if(previousManager != null) {
            roleChecker.checkToDemote(previousManager);
        }
    }

    @Transactional
    public void changeRole(ApplicationUser user, ERole erole) {
        Set<Role> roles = new HashSet<>();
        Role newRole = roleRepository.findByName(erole)
                .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));
        roles.add(newRole);

        user.setRoles(roles);
        userService.update(user);
    }
}
