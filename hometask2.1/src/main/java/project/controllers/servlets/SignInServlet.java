package project.controllers.servlets;


import project.dto.User;
import project.services.ChatService;
import project.services.api.IChatService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

    private IChatService chatService = ChatService.getChatService();
    private final String URL_PROFILE ="/messenger/profile.jsp";
    private final String URL_SIGN_IN = "/messenger/signIn.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (chatService.haveUserExistence(login) && chatService.checkPassword(login, password)) {
            HttpSession session1 = req.getSession();
            session1.setAttribute("login", login);
            User user= chatService.getUser(login);
            session1.setAttribute("profileInfo", user);
            req.getRequestDispatcher(URL_PROFILE).forward(req, resp);
        } else {
            req.setAttribute("errorIn", "incorrect login or password");
            req.getRequestDispatcher(URL_SIGN_IN).forward(req, resp);
        }
    }


}
