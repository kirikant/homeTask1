package project.services.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IStatisticsService {
    void addSession();
    void removeSession();
    int getMessagesAmount();
    int getUsersAmount();
    void viewStatistics(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
