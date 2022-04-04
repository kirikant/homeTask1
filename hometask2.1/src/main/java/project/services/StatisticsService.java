package project.services;


import project.services.api.IStatisticsService;
import project.storages.StorageFactory;
import project.storages.api.IChatStorageWithAudit;


import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsService implements IStatisticsService {
    private static StatisticsService statisticsService = new StatisticsService();
    private AtomicInteger sessionCounter = new AtomicInteger(0);
    private IChatStorageWithAudit hibStorage = StorageFactory.getStorageFactory().getStorage();


    private StatisticsService() {
    }
    public static StatisticsService getStatisticsService() {
        return statisticsService;
    }

    public AtomicInteger getSessionCounter() {
        return sessionCounter;
    }

    public void addSession() {
        sessionCounter.incrementAndGet();
    }

    public void removeSession() {
        sessionCounter.decrementAndGet();
    }

    public long getMessagesAmount() {
        return hibStorage.getMessagesNumber();
    }

    public long getUsersAmount() {
        return hibStorage.getUsersNumber();
    }
}
