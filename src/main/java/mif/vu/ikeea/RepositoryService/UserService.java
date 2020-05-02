package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Transactional
    public ApplicationUser add(ApplicationUser user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        ApplicationUser user = loadById(id);
        ApplicationUser manager = user.getManager();
        manager.getChildren().remove(user);
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(ApplicationUser user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmailAndEnabled(email, true)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + email)
                );

        return new User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    @Transactional
    public ApplicationUser loadByEmail(String email) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + email)
                );

        return user;
    }

    @Transactional
    public ApplicationUser findByEmail(String email) throws ResourceNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with username or email : " + email)
                );

        return user;
    }

    @Transactional
    public Boolean existsByEmail(String email) throws UsernameNotFoundException {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public ApplicationUser loadById(Long id) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with")
                );

        return user;
    }

    @Transactional
    public ApplicationUser loadByToken(String token) throws ResourceNotFoundException {
        ApplicationUser user = userRepository.findByToken(token)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with token was not found : " + token)
                );

        return user;
    }

    @Transactional
    public Integer updateRestrictionDays(Integer restrictionDays, Set<Long> ids) {
        Integer updatedRows = userRepository.updateRestrictionDays(restrictionDays, ids);

        return updatedRows;
    }

    public List<ApplicationUser> getAll() {
        Iterable<ApplicationUser> userIterable = userRepository.findAll();
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        userIterable.forEach(applicationUsers::add);

        return applicationUsers;
    }

    public List<ApplicationUser> getAllByManagerId(Long userId) {
        Iterable<ApplicationUser> userIterable = userRepository.findAllByManagerId(userId);
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        userIterable.forEach(applicationUsers::add);

        return applicationUsers;
    }

    private Set getAuthority(ApplicationUser user) {
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public static <T extends Enum<T>> T getInstance(final String value, final Class<T> ERole) {
        return Enum.valueOf(ERole, value);
    }

    public void checkToPromote(ApplicationUser user) {
        //ERole[] roles = ERole.valueOf();
        ERole role = getInstance("DEVELOPER", ERole.class);
        if(user.getRoles().equals(role)) { //user.getRoles().equals(ERole.values()[0]), user.getRoles().equals(roles[0]), Enum.valueOf(ERole.class, "DEVELOPER")
            Role role1 = new Role();
            role1.setId(1);
            role1.setName(ERole.LEADER);
            user.setRoles(Collections.singleton(role1));
            userService.update(user);
        }

    }

}
