package project;

import project.dto.Message;
import project.dto.Pageable;
import project.dto.User;
import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.services.ChatService;
import project.storages.hib.HibChatStorageWithAudit;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Runner {
    public static void main(String[] args) {
        HibChatStorageWithAudit hib = HibChatStorageWithAudit.getHibChatStorageWithAudit();
        ChatService chatService = ChatService.getChatService();

     // chatService.addUser(new User("kirikant30", "1", "kir",""));
     //   System.out.println(chatService.getUserAudits(chatService.getUser("kirikantWEB")));
//  chatService.addMessage(new Message(LocalDateTime.now(), "kirikant30", "hi","kirikant30"));
//        System.out.println(chatService.checkPassword("kirikantDB96","1"));//.getUserMessages(
               // new User("kirikantDB96", "1", "kir", LocalDate.now().toString())));
   //     UserEntity userEntity = new UserEntity("kirikant", "1", "kir", LocalDate.now().toString());
 //  UserEntity userEntity = hib.getUser("kirikant");
  //     MessageEntity messageEntity = new MessageEntity(LocalDateTime.now(), "kirikant", "hi", userEntity);
//       hib.addUser(userEntity,
//               new AuditUserEntity(LocalDateTime.now(),"register",userEntity,userEntity));
   //    hib.addMessage(messageEntity,new AuditUserEntity(LocalDateTime.now(),"sending of the message",userEntity,userEntity));
    //    System.out.println(hib.getUserAudits(userEntity)); ;
    }
}
