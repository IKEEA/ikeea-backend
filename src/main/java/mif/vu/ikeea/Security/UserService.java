package mif.vu.ikeea.Security;

import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

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
}
