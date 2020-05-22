package mif.vu.ikeea.Messages;

public interface IMessage {
    String createMessage();

    void setParameter(String parameter);

    String getParameter();
}
