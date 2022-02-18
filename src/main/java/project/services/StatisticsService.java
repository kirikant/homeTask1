package project.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsService {
    private static StatisticsService statisticsService = new StatisticsService();
    private AtomicInteger sessionCounter = new AtomicInteger(0);
    private Storage storage = Storage.getStorage();

    private StatisticsService() {
    }

    public static StatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void addSession() {
        sessionCounter.incrementAndGet();
    }

    public void removeSession() {
        sessionCounter.decrementAndGet();
    }

    public int getMessagesAmount(){
               return  storage.getMessages().values().stream()
                .map(ArrayList::size)
                       .reduce(Integer::sum).orElse(0);
           }
    public int getUsersAmount(){
        return storage.getUsersLogins().size();
    }

    public void viewStatistics(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sessionsAmount",sessionCounter);
        req.setAttribute("usersAmount",getUsersAmount());
        req.setAttribute("messagesAmount",getMessagesAmount());
        req.getRequestDispatcher("/messenger/statistics.jsp").forward(req,resp);
    }
}
