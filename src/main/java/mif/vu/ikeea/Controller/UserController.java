package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Exceptions.DuplicateResourceException;
import mif.vu.ikeea.Factory.MessageFactory;
import mif.vu.ikeea.Mailer.EmailService;
import mif.vu.ikeea.Manager.UserManager;
import mif.vu.ikeea.Payload.UpdateProfileRequest;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PreAuthorize("hasRole('LEADER')")
    @PostMapping("/invite")
    public ResponseEntity<?> inviteUser(@Valid @RequestParam String email, Authentication authentication) {
        if (userService.existsByEmail(email)) {
            throw new DuplicateResourceException("User with this email exist!");
        }

        ApplicationUser manager = (ApplicationUser) authentication.getPrincipal();

        ApplicationUser user = userManager.create(email, manager);
        String message = MessageFactory.verifyEmail(user.getToken());
        emailService.sendSimpleMessage(user.getEmail(), "Verify your account", message);

        return ResponseEntity.ok(new ApiResponse(true, "User invited successfully"));
    }

    @GetMapping(path = "/profile")
    public @ResponseBody UserProfileResponse profile(Authentication authentication) {
        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();

        return new UserProfileResponse(user);
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody UserProfileResponse get(@PathVariable Long id) {
        ApplicationUser user = userService.loadById(id);

        return new UserProfileResponse(user);
    }

    @PreAuthorize("hasRole('LEADER')")
    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.ok(new ApiResponse(true,"User deleted successfully"));
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody UserProfileResponse update(@PathVariable Long id, @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        ApplicationUser user = userService.loadById(id);

        return userManager.update(user, updateProfileRequest);
    }

    @PreAuthorize("hasRole('LEADER')")
    @PutMapping(path = "/{id}/update-restriction-days")
    public @ResponseBody ResponseEntity updateRestrictionDays(@PathVariable Long id, @RequestParam Integer restrictionDays) {
        ApplicationUser user = userService.loadById(id);
        user.setRestrictionDays(restrictionDays);
        userService.update(user);

        return ResponseEntity.ok(new ApiResponse(true, "User restriction days updated successfully"));
    }

    @PreAuthorize("hasRole('LEADER')")
    @GetMapping(path = "/list")
    public @ResponseBody
    List<UserProfileResponse> list(){
        List<ApplicationUser> users = userService.getAll();
        List<UserProfileResponse> userProfileResponses = new ArrayList<>();

        for (ApplicationUser applicationUser : users) {
            userProfileResponses.add(new UserProfileResponse(applicationUser));
        }

        return userProfileResponses;
    }

}
