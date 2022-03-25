package project.controllers.servlets;

import project.dto.Message;
import project.services.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChatsServlet", urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {

    private ChatService chatService = ChatService.getChatService();
    private final String URL_CHATS = "/messenger/chats.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session1 = req.getSession();
        String login = (String) session1.getAttribute("login");

        List<Message> messagesList = chatService.getUserMessages(login);
        req.setAttribute("messagesList", messagesList);
        req.getRequestDispatcher(URL_CHATS).forward(req, resp);
    }
}
