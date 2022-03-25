package project.services.api;

public interface IStatisticsService {
    void addSession();
    void removeSession();
    long getMessagesAmount();
    long getUsersAmount();
}
