package project.storages.hib;

import project.dto.Pageable;
import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.storages.api.IAuditStorage;
import project.storages.api.IChatStorageWithAudit;
import project.utils.EntityFactoryInitializer;

import javax.persistence.EntityManager;
import java.util.List;

public class HibChatStorageWithAudit implements IChatStorageWithAudit {
    private EntityFactoryInitializer factoryInitializer;

    private HibChatStorageWithAudit() {
        factoryInitializer = EntityFactoryInitializer.getInstance();
    }

    private HibStorage hibStorage = HibStorage.getHibStorage();
    private IAuditStorage<EntityManager> hibAuditStorage = HibAuditStorage.getHibAuditStorage();

    private static HibChatStorageWithAudit hibChatStorageWithAudit = new HibChatStorageWithAudit();

    public static HibChatStorageWithAudit getHibChatStorageWithAudit() {
        return hibChatStorageWithAudit;
    }


    public void addUser(UserEntity userEntity, AuditUserEntity auditUserEntity) {
        EntityManager entityManager = factoryInitializer.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            hibStorage.addUser(userEntity,entityManager);
            auditUserEntity.setUserAudit(userEntity);
            auditUserEntity.setAuthor(userEntity);
            hibAuditStorage.addAudit(auditUserEntity,entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }

    public void addMessage(MessageEntity messageEntity, AuditUserEntity auditUserEntity) {
        EntityManager entityManager = factoryInitializer.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            UserEntity userEntity = hibStorage.getUser(messageEntity.getLoginReceiver().getLogin(),
                    entityManager);
            hibStorage.addMessage(messageEntity,entityManager);
            auditUserEntity.setUserAudit(userEntity);
            auditUserEntity.setAuthor(userEntity);
            hibAuditStorage.addAudit(auditUserEntity,entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }

    public List<AuditUserEntity> getUserAudits(UserEntity userEntity) {
        List<AuditUserEntity> auditUserEntities = null;
        EntityManager entityManager = factoryInitializer.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            UserEntity user = hibStorage.getUser(userEntity.getLogin(),entityManager);
            auditUserEntities = hibAuditStorage.readAudit(user,entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return auditUserEntities;
    }

    public long getUsersNumber() {
        return hibStorage.getUsersNumber();
    }

    public long getMessagesNumber() {
        return hibStorage.getMessagesNumber();
    }

    public boolean haveUserExistence(String login) {
        return hibStorage.haveUserExistence(login);
    }

    public UserEntity getUser(String login) {
        return hibStorage.getUser(login);
    }

    public List<MessageEntity> getUserMessages(UserEntity userEntity,Pageable pageable) {
        return hibStorage.getUserMessages(userEntity,pageable);
    }

    public boolean checkPassword(String login, String password) {
        return hibStorage.checkPassword(login, password);
    }


}
