package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Mailer.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String sendEmail() {
        emailService.sendSimpleMessage("jemisardas@gmail.com", "Helo", "zdrv");

        return "Email sent successfully";
    }
}
