package project.controllers.listeners;

import project.services.StatisticsService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    StatisticsService statisticsService = StatisticsService.getStatisticsService();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        statisticsService.addSession();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        statisticsService.removeSession();
    }
}
