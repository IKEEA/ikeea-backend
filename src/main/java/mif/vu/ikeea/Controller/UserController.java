package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.MessageFactory;
import mif.vu.ikeea.Mailer.EmailService;
import mif.vu.ikeea.Manager.UserManager;
import mif.vu.ikeea.Payload.UpdateProfileRequest;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    @Autowired
    EmailService emailService;

    @PostMapping("/invite")
    public ResponseEntity<?> inviteUser(@Valid @RequestParam String email, Authentication authentication) {
        if(userRepository.existsByEmail(email)) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        ApplicationUser manager = (ApplicationUser) authentication.getPrincipal();

        //TODO add check if this user has manager role
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
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Empty User");
        }
        return new UserProfileResponse(optionalUser.get());
    }

    @GetMapping(path = "/list")
    public @ResponseBody Iterable<ApplicationUser> list(){
        return userRepository.findAll();
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Empty User");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true,"User deleted successfully"));
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity updateEmail(@PathVariable Long id, @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Very bad");
        }

        userManager.update(optionalUser.get(), updateProfileRequest);

        return ResponseEntity.ok(new ApiResponse(true, "User e-mail updated successfully"));
    }

    @PutMapping(path = "/{id}/update-restriction-days")
    public @ResponseBody ResponseEntity updateRestrictionDays(@PathVariable Long id, @RequestParam Integer restrictionDays) {
        Optional<ApplicationUser> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("User not found");
        }

        ApplicationUser user = optionalUser.get();
        user.setRestrictionDays(restrictionDays);
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User restriction days updated successfully"));
    }
}
