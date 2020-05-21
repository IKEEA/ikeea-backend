package mif.vu.ikeea.Mailer;

public interface IEmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
