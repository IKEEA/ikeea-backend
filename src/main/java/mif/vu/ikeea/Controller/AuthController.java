package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Manager.UserManager;
import mif.vu.ikeea.Payload.RegistrationRequest;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.JwtAuthenticationResponse;
import mif.vu.ikeea.Payload.LoginRequest;
import mif.vu.ikeea.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

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
        Optional<ApplicationUser> optionalUser = userRepository.findByToken(token);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(new ApiResponse(false, "User with this token doesn't exist"),
                    HttpStatus.BAD_REQUEST);
        }

        ApplicationUser user = optionalUser.get();

        return ResponseEntity.ok(new ApiResponse(true, user.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        Optional<ApplicationUser> optionalUser = userRepository.findByEmail(registrationRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(new ApiResponse(false, "User with that credentials doesn't exist"),
                    HttpStatus.BAD_REQUEST);
        }

        userManager.finishRegistration(optionalUser.get(), registrationRequest);

        return ResponseEntity.ok(new ApiResponse(true, "User was register"));
    }
}
