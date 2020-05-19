package mif.vu.ikeea.Checker;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Helper.PromotionHelper;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionChecker {
    @Autowired
    private UserService userService;

    @Autowired
    private PromotionHelper promotionHelper;

    public void isEligibleToPromote(ApplicationUser user) {
        for (Role role : user.getRoles()) {
            if (role.getName().equals(ERole.LEADER)) {
                continue;
            }
            promotionHelper.changeRole(user, ERole.LEADER);
        }
    }

    public void isEligibleToDemote(ApplicationUser user) {
        List<ApplicationUser> applicationUsers = userService.getAllByManagerId(user.getId());

        if(!applicationUsers.isEmpty()) {
            return;
        }
        promotionHelper.changeRole(user, ERole.DEVELOPER);
    }
}
