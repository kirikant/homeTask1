package project.services.api;

import java.util.concurrent.atomic.AtomicInteger;

public interface IStatisticsService {
    AtomicInteger getSessionCounter();

    void addSession();
    void removeSession();
    long getMessagesAmount();
    long getUsersAmount();
}
