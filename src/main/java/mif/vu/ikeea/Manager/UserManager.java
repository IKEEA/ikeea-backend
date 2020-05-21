package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Checker.PasswordChecker;
import mif.vu.ikeea.Entity.Repository.RoleRepository;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.*;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Generator.TokenValueGenerator;
import mif.vu.ikeea.Helper.PromotionHelper;
import mif.vu.ikeea.Helper.RestrictionDaysHelper;
import mif.vu.ikeea.Payload.RegistrationRequest;
import mif.vu.ikeea.Payload.UpdateForLeaderRequest;
import mif.vu.ikeea.Payload.UpdateProfileRequest;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class UserManager
{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordChecker passwordChecker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestrictionDaysHelper restrictionDaysHelper;

    @Autowired
    private PromotionHelper promotionHelper;

    @Transactional
    public ApplicationUser create(String email, ApplicationUser manager) {
        String generatedPassword = TokenValueGenerator.generate();
        String password = passwordEncoder.encode(generatedPassword);

        Role role = roleRepository.findByName(ERole.DEVELOPER)
                .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));

        ApplicationUser user = UserFactory.createUser(
                email,
                role,
                password,
                manager,
                TokenValueGenerator.generate()
        );

        ApplicationUser result = userService.add(user);

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

        userService.update(user);
    }

    @Transactional
    public UserProfileResponse update(ApplicationUser user, UpdateProfileRequest userProfileRequest) {
        if (userProfileRequest.getEmail() != null) {
            user.setEmail(userProfileRequest.getEmail());
        }

        if (userProfileRequest.getFirstName() != null) {
            user.setFirstName(userProfileRequest.getFirstName());
        }

        if (userProfileRequest.getLastName() != null) {
            user.setLastName(userProfileRequest.getLastName());
        }

        if (userProfileRequest.getPassword() != null && userProfileRequest.getOldPassword() != null) {
            updatePassword(user, userProfileRequest);
        }

        userService.update(user);

        return new UserProfileResponse(user);
    }

    @Transactional
    public UserProfileResponse updateForLeader(ApplicationUser user, UpdateForLeaderRequest updateForLeaderRequest) {
        if (updateForLeaderRequest.getRestrictionDays() != null) {
            user.setRestrictionDays(updateForLeaderRequest.getRestrictionDays());
        }

        if (updateForLeaderRequest.getManagerId() != null) {
            promotionHelper.updateRoles(user, updateForLeaderRequest);
        }

        userService.update(user);

        return new UserProfileResponse(user);
    }

    @Transactional
    public UserProfileResponse safeDeleteUser(Long id) {
        ApplicationUser user = userService.loadById(id);
        ApplicationUser manager = user.getManager();
        userService.delete(id);
        promotionHelper.demoteUser(manager);

        return new UserProfileResponse(user);
    }

    public void updateRestrictionDay(List<ApplicationUser> managersChildren, Integer restrictionDays) {
        Set<Long> userIds = restrictionDaysHelper.collectUserIds(managersChildren);
        userService.updateRestrictionDays(restrictionDays, userIds);
    }

    private void updatePassword(ApplicationUser user, UpdateProfileRequest profileRequest) {
        if (!passwordChecker.checkIfValidPassword(user, profileRequest.getOldPassword())) {
            throw new PasswordDoesNotMatchException("Your old password doesn't match!");
        }

        if (passwordChecker.checkIfValidPassword(user, profileRequest.getPassword())) {
            throw new PasswordMatchException("You can't use old password!");
        }

        String userPassword = passwordEncoder.encode(profileRequest.getPassword());
        user.setPassword(userPassword);
    }
}