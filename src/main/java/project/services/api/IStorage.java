package project.services.api;

import project.dto.Message;
import project.dto.User;

import java.util.ArrayList;
import java.util.Map;

public interface IStorage {
    Map<String, User> getUsersLogins();
    Map<String, ArrayList<Message>> getMessages();
}
