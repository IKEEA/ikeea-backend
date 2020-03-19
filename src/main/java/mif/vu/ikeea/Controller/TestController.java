package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Employee;
import mif.vu.ikeea.Entity.Repository.EmployeeRepository;
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
        private EmployeeRepository userRepository;

        @PostMapping(path = "/add")
        public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email){
        Employee n = new Employee();
        userRepository.save(n);
        return "Saved";
    }

        @GetMapping(path = "/all")
        public @ResponseBody Iterable<Employee> getAllUsers () {
        return userRepository.findAll();
    }

}
