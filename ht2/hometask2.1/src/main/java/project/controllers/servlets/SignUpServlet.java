package project.controllers.servlets;


import project.dto.User;
import project.services.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
    private ChatService chatService = ChatService.getChatService();
    private final String URL_PROFILE ="/messenger/profile.jsp";
    private final String URL_SIGN_UP = "/messenger/signUp.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.equals("") || password.equals("")) {
            req.setAttribute("errorUp", "enter login and password correctly");
            req.getRequestDispatcher(URL_SIGN_UP).forward(req, resp);
        } else {
            if (chatService.haveUserExistence(login)) {
                req.setAttribute("errorUp", "login is already existed");
                req.getRequestDispatcher(URL_SIGN_UP).forward(req, resp);
            } else {
                HttpSession session1 = req.getSession();
                session1.setAttribute("login", login);
                chatService.addUser(new User(login, password, req.getParameter("name"), req.getParameter("birthDay")));
                User user = chatService.getUser(login);

                session1.setAttribute("profileInfo", user);
                req.getRequestDispatcher(URL_PROFILE).forward(req, resp);
            }

        }

    }


}
