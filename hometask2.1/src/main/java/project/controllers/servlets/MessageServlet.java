package project.controllers.servlets;

import project.dto.Message;
import project.services.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "Message", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private ChatService chatService = ChatService.getChatService();
    private final String URL_PROFILE ="/messenger/profile.jsp";
    private final String URL_MESSAGE ="/messenger/message.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String loginFrom = (String) req.getSession().getAttribute("login");
        String receiver = req.getParameter("receiver");

        if (!chatService.haveUserExistence(receiver)) {
            req.setAttribute("errorUp", "such receiver is not existed");
            req.getRequestDispatcher(URL_MESSAGE).forward(req, resp);
        }else {chatService.addMessage(new Message(LocalDateTime.now(),loginFrom,req.getParameter("message"),receiver));
            req.getRequestDispatcher(URL_PROFILE).forward(req, resp);}

    }
}
