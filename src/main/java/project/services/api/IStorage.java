package project.services.api;

import project.dto.Message;
import project.dto.User;
import project.services.Storage;

import java.util.ArrayList;
import java.util.Map;

public interface IStorage {
    Map<String, User> getUsersLogins();
    Map<String, ArrayList<Message>> getMessages();
    boolean haveUserExistence(String login);
    boolean checkPassword(String login, String password);
    void addUser(String login, String password, String name, String birthday);
    User getUser(String login);
    void addMessage(String receiver, String loginFrom, String message);
    ArrayList<Message> getMessageList(String login);
}
