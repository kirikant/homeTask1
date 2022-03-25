package project.services.api;

import project.dto.Message;
import project.dto.User;
import project.entity.MessageEntity;
import project.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IChatService {
    void addUser(User user);
    void addMessage(Message message);
    boolean haveUserExistence(String login);
    User getUser(String login);
    List<Message> getUserMessages(String login);
    boolean checkPassword (String login, String password);


}
