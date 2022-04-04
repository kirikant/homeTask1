package project.services.api;

import project.dto.AuditUser;
import project.dto.Message;
import project.dto.Pageable;
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

    List<AuditUser> getUserAudits(User user);

    boolean haveUserExistence(String login);
    User getUser(String login);
    List<Message> getUserMessages(User user, Pageable pageable);
    boolean checkPassword (String login, String password);


}
