package project.controllers.servlets;

import project.services.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "StatisticsServlet", urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {

   private StatisticsService statisticsService = StatisticsService.getStatisticsService();
   private final String URL_STATISTICS="/messenger/statistics.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sessionsAmount",statisticsService.getSessionCounter());
        req.setAttribute("usersAmount",statisticsService.getUsersAmount());
        req.setAttribute("messagesAmount",statisticsService.getMessagesAmount());
        req.getRequestDispatcher(URL_STATISTICS).forward(req,resp);
    }
}
