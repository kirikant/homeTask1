package project.services;

import project.dto.Message;
import project.dto.User;
import project.services.api.IStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage implements IStorage {
  private  static  Storage storage = new Storage();
  private Map<String, User> usersLogins = new HashMap<>();
private  Map<String, ArrayList<Message>> messages = new HashMap<>();

  private Storage() {
  }

  public static Storage getStorage() {
    return storage;
  }

  public Map<String, User> getUsersLogins() {
    return usersLogins;
  }

  public Map<String, ArrayList<Message>> getMessages() {
    return messages;
  }
}
