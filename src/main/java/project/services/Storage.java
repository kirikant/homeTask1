package project.services;

import project.dto.Message;
import project.dto.User;
import project.services.api.IStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Storage implements IStorage {
    private static Storage storage = new Storage();
    private Map<String, User> usersLogins = new HashMap<>();
    private Map<String, ArrayList<Message>> messages = new HashMap<>();

    private Storage() {
    }

    public Map<String, User> getUsersLogins() {
        return Collections.unmodifiableMap(usersLogins);
    }

    public Map<String, ArrayList<Message>> getMessages() {
        return Collections.unmodifiableMap(messages);
    }

    public boolean haveUserExistence(String login) {
        return storage.usersLogins.containsKey(login);
    }

    public boolean checkPassword(String login, String password) {
        return usersLogins.get(login).getPassword().equals(password);
    }

    public void addUser(String login, String password, String name, String birthday) {
        storage.usersLogins.put(login, new User(login, password, name, birthday));
    }

    public User getUser(String login) {
        return usersLogins.get(login);
    }

    public void addMessage(String receiver, String loginFrom, String message) {
        if (messages.containsKey(receiver)) {
            messages.get(receiver)
                    .add(new Message(LocalDateTime.now(), loginFrom, message));
        } else {
            messages.put(receiver, new ArrayList<>());
            messages.get(receiver)
                    .add(new Message(LocalDateTime.now(), loginFrom, message));
        }
    }

    public ArrayList<Message> getMessageList(String login) {
        return storage.messages.get(login);
    }

    public static Storage getStorage() {
        return storage;
    }
}
