package project.services;

import project.dto.Message;
import project.dto.User;
import project.services.api.IChatService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class ChatService implements IChatService {
    private Storage storage = Storage.getStorage();
    private static ChatService chatService = new ChatService();

    private ChatService() {
    }

    public static ChatService getChatService() {
        return chatService;
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.equals("") || password.equals("")) {
            req.setAttribute("errorUp", "enter login and password correctly");
            req.getRequestDispatcher("/messenger/signUp.jsp").forward(req, resp);
        } else {
            if (storage.getUsersLogins().containsKey(login)) {
                req.setAttribute("errorUp", "login is already existed");
                req.getRequestDispatcher("/messenger/signUp.jsp").forward(req, resp);
            } else {
                HttpSession session1 = req.getSession();
                session1.setAttribute("login", login);
                storage.getUsersLogins().put(login, new User(login, password, req.getParameter("name"),
                        req.getParameter("birthDay")));
                viewProfile(req,resp);
                req.getRequestDispatcher("/messenger/profile.jsp").forward(req, resp);
            }

        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String login = req.getParameter("login");
        Map<String, User> usersLogins = storage.getUsersLogins();

        if (usersLogins.containsKey(login) &&
                usersLogins.get(login).getPassword().equals(req.getParameter("password"))) {
            HttpSession session1 = req.getSession();
            session1.setAttribute("login", login);
            viewProfile(req, resp);
            req.getRequestDispatcher("/messenger/profile.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorIn", "incorrect login or password");
            req.getRequestDispatcher("/messenger/signIn.jsp").forward(req, resp);

        }
    }

    public void viewProfile(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String login = req.getParameter("login");
        Map<String, User> usersLogins = storage.getUsersLogins();
        User user = usersLogins.get(login);
        HttpSession session1 = req.getSession();
        session1.setAttribute("profileInfo", user);
    }

    public void createMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String loginFrom = (String) req.getSession().getAttribute("login");
        String receiver = req.getParameter("receiver");
        Map<String, ArrayList<Message>> messages = storage.getMessages();
        if (messages.containsKey(receiver)) {
            messages.get(receiver)
                    .add(new Message(LocalDateTime.now(), loginFrom, req.getParameter("message")));
        } else {
            messages.put(receiver, new ArrayList<>());
            messages.get(receiver)
                    .add(new Message(LocalDateTime.now(), loginFrom, req.getParameter("message")));
        }
        req.getRequestDispatcher("/messenger/profile.jsp").forward(req, resp);
    }

    public void viewChats(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session1 = req.getSession();
        String login = (String) session1.getAttribute("login");
        Map<String, ArrayList<Message>> messages = storage.getMessages();
        ArrayList<Message> messagesList = messages.get(login);
        req.setAttribute("messagesList", messagesList);
        req.getRequestDispatcher("/messenger/chats.jsp").forward(req, resp);
    }
}
