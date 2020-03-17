package mif.vu.ikeea.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
public class EmailController {

    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws AddressException, MessagingException, IOException {
       // JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

//        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

//        EmailServiceImpl emailService = (EmailServiceImpl) context.getBean("profile");
//        profile.printAge();
//        profile.printName();



        EmailServiceImpl emailService = new EmailServiceImpl();

        emailService.sendSimpleMessage("ievaviz92@gmail.com", "Helo", "zdrv");

        return "Email sent successfully";
    }

    @PostConstruct
    protected void iamAlive(){
        System.out.println("Bye-bye");
    }


}
