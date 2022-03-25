package project.storages;

import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.storages.api.IStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapStorage implements IStorage {
    private static MapStorage storage = new MapStorage();
    private Map<String, UserEntity> usersLogins = new HashMap<>();
    private Map<String, ArrayList<MessageEntity>> messages = new HashMap<>();

    private MapStorage() {
    }

    public long getUsersNumber() {
        return usersLogins.keySet().size();
    }

    public boolean checkPassword(String login, String password) {
        return usersLogins.get(login).getPassword().equals(password);
    }

    public long getMessagesNumber() {
        return messages.values().stream()
                .map(ArrayList::size)
                .reduce(Integer::sum)
                .get().longValue();
    }

    public boolean haveUserExistence(String login) {
        return storage.usersLogins.containsKey(login);
    }

    public void addUser(UserEntity userEntity) {
        storage.usersLogins.put(userEntity.getLogin(), userEntity);
    }


    public void addUser(UserEntity userEntity, AuditUserEntity auditUserEntity) {

    }

    public UserEntity getUser(String login) {
        return usersLogins.get(login);
    }

    public void addMessage(MessageEntity messageEntity) {
        if (messages.containsKey(messageEntity.getLoginReceiver())) {
            messages.get(messageEntity.getLoginReceiver())
                    .add(messageEntity);
        } else {
            messages.put(messageEntity.getLoginReceiver(), new ArrayList<>());
            messages.get(messageEntity.getLoginReceiver())
                    .add(messageEntity);
        }
    }


    public void addMessage(MessageEntity messageEntity, AuditUserEntity auditUserEntity) {

    }

    public ArrayList<MessageEntity> getUserMessages(String login) {
        return messages.get(login);
    }

    public static MapStorage getStorage() {
        return storage;
    }
}
