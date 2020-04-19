package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Exceptions.DuplicateResourceException;
import mif.vu.ikeea.Manager.UserManager;
import mif.vu.ikeea.Payload.RegistrationRequest;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.JwtAuthenticationResponse;
import mif.vu.ikeea.Payload.LoginRequest;
import mif.vu.ikeea.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserManager userManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/verify/{token}")
    public ResponseEntity<?> verifyToken(@PathVariable String token) {
        ApplicationUser user = userService.loadByToken(token);

        return ResponseEntity.ok(new ApiResponse(true, user.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        ApplicationUser user = userService.findByEmail(registrationRequest.getEmail());

        if (user.getToken() == null) {
            throw new DuplicateResourceException("User is already registered");
        }

        userManager.finishRegistration(user, registrationRequest);

        return ResponseEntity.ok(new ApiResponse(true, "User register successfully"));
    }
}
