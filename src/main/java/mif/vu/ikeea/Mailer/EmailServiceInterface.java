package mif.vu.ikeea.Mailer;

public interface EmailServiceInterface {
    void sendSimpleMessage(String to, String subject, String text);
}
