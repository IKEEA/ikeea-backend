package mif.vu.ikeea.Factory;

public class MessageFactory {
    public static String verifyEmail(String token) {
        return "Hello, you can verify your account by clicking: " + token;
    }
}
