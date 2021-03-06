package project.utils.transformers;

import project.dto.Message;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.storages.StorageFactory;
import project.storages.api.IChatStorageWithAudit;
import project.utils.api.ITransformer;

import java.util.ArrayList;
import java.util.List;

public class MessageTransformer implements ITransformer<MessageEntity, Message> {
    private static MessageTransformer transformer = new MessageTransformer();
    private IChatStorageWithAudit storage = StorageFactory.getStorageFactory().getStorage();

    private MessageTransformer() {
    }

    public static MessageTransformer getTransformer() {
        return transformer;
    }


    public Message entityToDto(MessageEntity messageEntity) {
        return new Message(messageEntity.getId(),
                messageEntity.getDateTime(),
                messageEntity.getLoginSender(),
                messageEntity.getText(),
                messageEntity.getLoginReceiver().getLogin());
    }

    public MessageEntity dtoToEntity(Message message) {
        return new MessageEntity(message.getDateTime(),
                message.getLoginSender(),
                message.getText(),
                storage.getUser(message.getLoginReceiver()));
    }

    public List<Message> listEntityToListDto(List<MessageEntity> messageEntities) {
        ArrayList<Message> messages = new ArrayList<>();
        for (MessageEntity messageEntity : messageEntities) {
            messages.add(entityToDto(messageEntity));
        }
        return messages;
    }


}
