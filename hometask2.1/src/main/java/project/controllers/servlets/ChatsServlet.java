package project.controllers.servlets;

import project.dto.Message;
import project.dto.Pageable;
import project.dto.User;
import project.services.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ChatsServlet", urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {

    private ChatService chatService = ChatService.getChatService();
    private final String URL_CHATS = "/messenger/chats.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session1 = req.getSession();
        User user = (User) session1.getAttribute("profileInfo");
        session1.setAttribute("page", 1);
        List<Message> messagesList = chatService.getUserMessages(user, new Pageable(3, 1));
      session1.setAttribute("size1",messagesList.size());
        req.setAttribute("messagesList", messagesList);
        req.getRequestDispatcher(URL_CHATS).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session1 = req.getSession();
        User user = (User) session1.getAttribute("profileInfo");
        String up = (String) req.getParameter("up");
        String down = (String) req.getParameter("down");
        Integer page = (Integer) session1.getAttribute("page");
        if (Objects.equals(up, "up")) {
            page = page + 1;
            session1.setAttribute("page", page);
        }
        if (Objects.equals(down, "down")){
            page = page - 1;
            session1.setAttribute("page", page);
        }
        req.setAttribute("step",null);
        List<Message> messagesList = chatService
                .getUserMessages(user, new Pageable(3, page));
       session1.setAttribute("size1",messagesList.size());
        req.setAttribute("messagesList", messagesList);
        req.getRequestDispatcher(URL_CHATS).forward(req, resp);
    }
}
