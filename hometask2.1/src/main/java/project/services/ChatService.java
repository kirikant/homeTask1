package project.services;

import project.dto.AuditUser;
import project.dto.Message;
import project.dto.Pageable;
import project.dto.User;
import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.services.api.IChatService;
import project.storages.StorageFactory;
import project.storages.api.IChatStorageWithAudit;
import project.storages.api.IStorage;
import project.utils.api.ITransformer;
import project.utils.transformers.AuditTransformer;
import project.utils.transformers.MessageTransformer;
import project.utils.transformers.UserTransformer;

import java.time.LocalDateTime;
import java.util.List;

public class ChatService implements IChatService {
    private IChatStorageWithAudit storage = StorageFactory.getStorageFactory().getStorage();
    private static ChatService chatService = new ChatService();
    private ITransformer<MessageEntity, Message> messageTransformer = MessageTransformer.getTransformer();
    private ITransformer<AuditUserEntity, AuditUser> auditTransformer = AuditTransformer.getTransformer();
    private ITransformer<UserEntity, User> userTransformer = UserTransformer.getTransformer();


    private ChatService() {
    }

    public static ChatService getChatService() {
        return chatService;
    }

    @Override
    public void addUser(User user) {
        AuditUser auditUser = new AuditUser(LocalDateTime.now(),
                "registration", user.getLogin(), user.getLogin());

        storage.addUser(userTransformer.dtoToEntity(user)
                , auditTransformer.dtoToEntity(auditUser));
    }


    @Override
    public void addMessage(Message message) {
        AuditUser auditUser = new AuditUser(LocalDateTime.now(),
                "send message",
                message.getLoginSender(),
                message.getLoginSender());
        storage.addMessage(messageTransformer.dtoToEntity(message),
                auditTransformer.dtoToEntity(auditUser));
    }

    public List<AuditUser> getUserAudits(User user) {
        return auditTransformer.listEntityToListDto(storage
                .getUserAudits(userTransformer.dtoToEntity(user)));
    }

    @Override
    public boolean haveUserExistence(String login) {
        return storage.haveUserExistence(login);
    }

    @Override
    public User getUser(String login) {
        return userTransformer.entityToDto(storage.getUser(login));
    }

    @Override
    public List<Message> getUserMessages(User user, Pageable pageable) {
        return messageTransformer.listEntityToListDto(storage
                .getUserMessages(userTransformer.dtoToEntity(user),pageable));
    }

    @Override
    public boolean checkPassword(String login, String password) {
        return storage.checkPassword(login, password);
    }


}
