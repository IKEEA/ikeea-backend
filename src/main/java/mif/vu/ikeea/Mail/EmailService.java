package mif.vu.ikeea.Mail;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
