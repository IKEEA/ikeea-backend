package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class TestController {

        @Autowired
        private UserRepository userRepository;

        @PostMapping(path = "/add")
        public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email){
        User n = new User();
        userRepository.save(n);
        return "Saved";
    }

        @GetMapping(path = "/all")
        public @ResponseBody Iterable<User> getAllUsers () {
        return userRepository.findAll();
    }

}
