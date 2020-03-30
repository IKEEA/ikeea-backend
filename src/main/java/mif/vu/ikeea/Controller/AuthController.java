package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Manager.UserCreationManager;
import mif.vu.ikeea.Payload.ApiResponse;
import mif.vu.ikeea.Payload.JwtAuthenticationResponse;
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
    UserCreationManager userCreationManager;

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
    public ResponseEntity<?> verifyUser(@PathVariable String token) {
        Optional<User> optionalUser = userRepository.findByToken(token);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(new ApiResponse(false, "User with that token doesn't exist"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();
        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User was verified"));
    }
}
