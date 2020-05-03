package mif.vu.ikeea.Helper;

import mif.vu.ikeea.Checker.PromotionChecker;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Payload.UpdateForLeaderRequest;
import mif.vu.ikeea.RepositoryService.RoleService;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PromotionHelper {
    @Autowired
    private UserService userService;

    @Autowired
    private PromotionChecker promotionChecker;

    @Autowired
    private RoleService roleService;

    public void updateRoles(ApplicationUser user, UpdateForLeaderRequest updateForLeaderRequest) {
        ApplicationUser previousManager = user.getManager();
        ApplicationUser manager = userService.loadById(updateForLeaderRequest.getManagerId());

        promotionChecker.isEligibleToPromote(manager);
        user.setManager(manager);

        if (previousManager != null) {
            promotionChecker.isEligibleToDemote(previousManager);
        }
    }

    public void changeRole(ApplicationUser user, ERole erole) {
        Set<Role> roles = new HashSet<>();
        Role role = roleService.findByName(erole);
        roles.add(role);
        user.setRoles(roles);
    }
}
