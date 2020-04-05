package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.MessageFactory;
import mif.vu.ikeea.Mailer.EmailService;
import mif.vu.ikeea.Manager.UserCreationManager;
import mif.vu.ikeea.Payload.ApiResponse;
import mif.vu.ikeea.Payload.RegistrationRequest;
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
    private UserCreationManager userCreationManager;

    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest, Authentication authentication) {
        if(userRepository.existsByEmail(registrationRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        User manager = (User) authentication.getDetails();

        //TODO add check if this user has manager role
        User user = userCreationManager.create(registrationRequest, manager);
        String message = MessageFactory.verifyEmail(user.getToken());
        emailService.sendSimpleMessage(user.getEmail(), "Verify your account", message);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody Iterable<User> list(){
        return userRepository.findAll();
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody User updateEmail(@PathVariable Long id, @RequestParam String email) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Very bad");
        }

        User user = optionalUser.get();
        user.setEmail(email);
        userRepository.save(user);

        return user;
    }

    @PutMapping(path = "/{id}/update-password")
    public @ResponseBody User updatePassword(@PathVariable Long id, @RequestParam String password, @RequestParam String oldPassword) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Empty User");
        }

        User user = optionalUser.get();
        if (!userCreationManager.checkIfValidOldPassword(user, oldPassword)) {
            throw new BadRequestHttpException("Very bad");
        }

        userCreationManager.updatePassword(user, password);

        return user;
    }
}
