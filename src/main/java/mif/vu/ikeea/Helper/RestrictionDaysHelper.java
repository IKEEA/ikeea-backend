package mif.vu.ikeea.Helper;

import mif.vu.ikeea.Entity.ApplicationUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RestrictionDaysHelper {

    private final Set<Long> userIds = new HashSet<>();

    public Set<Long> collectUserIds(List<ApplicationUser> userList) {
        for (ApplicationUser user : userList) {
            userIds.add(user.getId());
            collectUserIds(user.getChildren());
        }

        return userIds;
    }
}
