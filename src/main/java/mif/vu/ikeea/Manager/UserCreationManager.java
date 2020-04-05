package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Repository.RoleRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Generator.TokenValueGenerator;
import mif.vu.ikeea.Payload.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserCreationManager
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User create(RegistrationRequest registrationRequest, User manager) {
        String password = passwordEncoder.encode(registrationRequest.getPassword());
        Role role = roleRepository.findByName(ERole.DEVELOPER)
                .orElseThrow(() -> new BadRequestHttpException("User Role not set."));
        String token = TokenValueGenerator.generate();

        User user = UserFactory.createUser(
                registrationRequest.getEmail(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                role,
                password,
                manager,
                manager.getTeam(),
                token
        );

        User result = userRepository.save(user);

        return result;
    }

    public User updatePassword(User user, String password) {
        String userPassword = passwordEncoder.encode(password);

        user.setPassword(userPassword);
        User result = userRepository.save(user);

        return result;
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword){
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}