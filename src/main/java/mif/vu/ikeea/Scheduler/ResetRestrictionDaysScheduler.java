package mif.vu.ikeea.Scheduler;

import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResetRestrictionDaysScheduler {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 1 */3 *")
    public void resetRestrictionDays() {
        Integer DEFAULT_RESTRICTION_DAYS = 3;

        userService.resetRestrictionDays(DEFAULT_RESTRICTION_DAYS);
    }
}
