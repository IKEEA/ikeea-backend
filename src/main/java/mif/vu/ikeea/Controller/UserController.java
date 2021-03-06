package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Exceptions.DuplicateResourceException;
import mif.vu.ikeea.Mailer.EmailService;
import mif.vu.ikeea.Manager.UserManager;
import mif.vu.ikeea.Messages.IMessage;
import mif.vu.ikeea.Payload.UpdateForLeaderRequest;
import mif.vu.ikeea.Payload.UpdateProfileRequest;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserManager userManager;

    @Autowired
    EmailService emailService;

    @Autowired
    @Qualifier("invitation_message")
    IMessage message;

    @PreAuthorize("hasRole('LEADER')")
    @PostMapping("/invite")
    public ResponseEntity<?> inviteUser(@Valid @RequestParam String email, Authentication authentication) {
        if (userService.existsByEmail(email)) {
            throw new DuplicateResourceException("User with this email exist!");
        }

        ApplicationUser manager = (ApplicationUser) authentication.getPrincipal();
        ApplicationUser user = userManager.create(email, manager);
        sendEmailMessage(user);

        return ResponseEntity.ok(new ApiResponse(true, "User invited successfully"));
    }

    @GetMapping(path = "/profile")
    public @ResponseBody UserProfileResponse profile(Authentication authentication) {
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();

        return new UserProfileResponse(user);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody UserProfileResponse get(@PathVariable Long id) {
        ApplicationUser user = userService.loadById(id);

        return new UserProfileResponse(user);
    }

    @PreAuthorize("hasRole('LEADER')")
    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        userManager.safeDeleteUser(id);

        return ResponseEntity.ok(new ApiResponse(true,"User deleted successfully"));
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody UserProfileResponse update(@PathVariable Long id, @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        ApplicationUser user = userService.loadById(id);

        return userManager.update(user, updateProfileRequest);
    }

    @PreAuthorize("hasRole('LEADER')")
    @PutMapping(path = "/{id}/update-for-leader")
    public @ResponseBody ResponseEntity updateForLeader(@PathVariable Long id, @Valid @RequestBody UpdateForLeaderRequest updateForLeaderRequest) {
        ApplicationUser user = userService.loadById(id);
        userManager.updateForLeader(user, updateForLeaderRequest);

        return ResponseEntity.ok(new ApiResponse(true, "User updated successfully"));
    }

    @PreAuthorize("hasRole('LEADER')")
    @GetMapping(path = "/list")
    public @ResponseBody List<UserProfileResponse> list(){
        List<ApplicationUser> users = userService.getAll();
        List<UserProfileResponse> userProfileResponses = new ArrayList<>();

        for (ApplicationUser applicationUser : users) {
            userProfileResponses.add(new UserProfileResponse(applicationUser));
        }

        return userProfileResponses;
    }

    @PreAuthorize("hasRole('LEADER')")
    @PostMapping(path = "/{managerId}/set-restriction-days")
    public @ResponseBody ResponseEntity setRestrictionDays(@PathVariable Long managerId, @RequestParam Integer restrictionDays) {
        ApplicationUser manager = userService.loadById(managerId);
        userManager.updateRestrictionDay(manager.getChildren(), restrictionDays);

        return ResponseEntity.ok(new ApiResponse(true, "Users restriction days updated successfully"));
    }

    private void sendEmailMessage(ApplicationUser user) {
        message.setParameter(user.getToken());
        String emailMessage = message.createMessage();
        emailService.sendSimpleMessage(user.getEmail(), "Verify your account", emailMessage);
    }
}
