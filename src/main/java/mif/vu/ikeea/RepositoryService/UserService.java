package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public ApplicationUser add(ApplicationUser user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void update(ApplicationUser user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + email)
                );

        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
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

    public List<ApplicationUser> getAll() {
        Iterable<ApplicationUser> userIterable = userRepository.findAll();
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        userIterable.forEach(applicationUsers::add);

        return applicationUsers;
    }
}
