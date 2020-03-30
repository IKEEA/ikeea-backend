package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Repository.RoleRepository;
import mif.vu.ikeea.Entity.Repository.TeamRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Payload.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserCreationManager
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TeamRepository teamRepository;

    public User create(RegistrationRequest registrationRequest) {

        Team team = teamRepository.findById(1L).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : "));

        User manager = userRepository.findById(1L).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : "));

        String password = passwordEncoder.encode(registrationRequest.getPassword());
        Role role = roleRepository.findByName(ERole.DEVELOPER)
                .orElseThrow(() -> new BadRequestHttpException("User Role not set."));

        User user = UserFactory.createUser(
                registrationRequest.getEmail(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                role,
                password,
                manager,
                team
        );

        User result = userRepository.save(user);

        return result;
    }
}