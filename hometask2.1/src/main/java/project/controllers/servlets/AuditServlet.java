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

@WebServlet (name = "AuditServlet", urlPatterns = "/audit")
public class AuditServlet extends HttpServlet {
    private IChatService chatService = ChatService.getChatService();
    private final String URL_AUDIT = "/messenger/audit.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        HttpSession session = req.getSession();
        session.setAttribute("audits",chatService
                .getUserAudits((User) session.getAttribute("profileInfo")));
        req.getRequestDispatcher(URL_AUDIT).forward(req, resp);
    }

}
