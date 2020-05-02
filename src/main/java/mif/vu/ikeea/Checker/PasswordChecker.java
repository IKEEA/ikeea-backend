package mif.vu.ikeea.Checker;

import mif.vu.ikeea.Entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordChecker {
    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean checkIfValidPassword(ApplicationUser user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
