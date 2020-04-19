package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Repository.RoleRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Generator.TokenValueGenerator;
import mif.vu.ikeea.Payload.RegistrationRequest;
import mif.vu.ikeea.Payload.UpdateProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserManager
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public ApplicationUser create(String email, ApplicationUser manager) {
        String generatedPassword = TokenValueGenerator.generate();
        String password = passwordEncoder.encode(generatedPassword);

        Role role = roleRepository.findByName(ERole.DEVELOPER)
                .orElseThrow(() -> new BadRequestHttpException("User Role not set."));

        ApplicationUser user = UserFactory.createUser(
                email,
                role,
                password,
                manager,
                manager.getTeam(),
                TokenValueGenerator.generate()
        );

        ApplicationUser result = userRepository.save(user);

        return result;
    }

    @Transactional
    public void finishRegistration(ApplicationUser user, RegistrationRequest registrationRequest) {
        user.setEnabled(true);
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        String password = passwordEncoder.encode(registrationRequest.getPassword());
        user.setPassword(password);
        user.setToken(null);

        userRepository.save(user);
    }

    @Transactional
    public ApplicationUser update(ApplicationUser user, UpdateProfileRequest userProfileRequest) {

        if (userProfileRequest.getEmail() != null) {
            user.setEmail(userProfileRequest.getEmail());
        }

        if (userProfileRequest.getFirstName() != null) {
            user.setFirstName(userProfileRequest.getFirstName());
        }

        if (userProfileRequest.getLastName() != null) {
            user.setLastName(userProfileRequest.getLastName());
        }

        if (userProfileRequest.getPassword() != null) {
            String userPassword = passwordEncoder.encode(userProfileRequest.getPassword());
            user.setPassword(userPassword);
        }

        ApplicationUser result = userRepository.save(user);

        return result;
    }

    private boolean checkIfValidOldPassword(ApplicationUser user, String oldPassword) {
        //TODO
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}