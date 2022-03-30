package project.storages.hib;


import project.dto.Pageable;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.utils.EntityFactoryInitializer;
import project.storages.api.IStorage;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibStorage implements IStorage<EntityManager> {
    private EntityFactoryInitializer factoryInitializer;

    private static HibStorage hibStorage = new HibStorage();

    public static HibStorage getHibStorage() {
        return hibStorage;
    }

    private HibStorage() {
        factoryInitializer = EntityFactoryInitializer.getInstance();
    }


    @Override
    public void addUser(UserEntity userEntity,EntityManager entityManager) {
        entityManager.persist(userEntity);
    }


    @Override
    public void addMessage(MessageEntity messageEntity, EntityManager entityManager) {
        entityManager.persist(messageEntity);
    }

    @Override
    public long getUsersNumber() {
        EntityManager entityManager = factoryInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query1 = cb.createQuery(UserEntity.class);
        Root<UserEntity> from = query1.from(UserEntity.class);
        long size = entityManager.createQuery(query1).getResultList().size();
        entityManager.getTransaction().commit();
        entityManager.close();
        return size;
    }

    @Override
    public long getMessagesNumber() {
        EntityManager entityManager = factoryInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query1 = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> from = query1.from(MessageEntity.class);
        long size = entityManager.createQuery(query1).getResultList().size();
        entityManager.getTransaction().commit();
        entityManager.close();
        return size;
    }

    @Override
    public boolean haveUserExistence(String login) {
        return hibStorage.getUser(login) != null;
    }

    @Override
    public List<MessageEntity> getUserMessages(UserEntity userEntity, Pageable pageable) {
        EntityManager entityManager = factoryInitializer.getEntityManager();
        UserEntity user = hibStorage.getUser(userEntity.getLogin());
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query1 = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> from = query1.from(MessageEntity.class);
        CriteriaQuery<MessageEntity> loginReceiver = query1
                .select(from)
                .where(cb.equal(from.get("loginReceiver"), user));
        TypedQuery<MessageEntity> query = entityManager.createQuery(loginReceiver);
       query.setFirstResult(pageable.getAmount() * (pageable.getPage() - 1));
        query.setMaxResults(pageable.getAmount());
        List<MessageEntity> resultList = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public boolean checkPassword(String login, String password) {
        return hibStorage.getUser(login).getPassword().equals(password);
    }


    public UserEntity getUser(String login) {
        EntityManager entityManager = factoryInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        UserEntity userEntity =  getUser(login,entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
        return userEntity;
    }

    @Override
    public  UserEntity getUser(String login,EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query1 = cb.createQuery(UserEntity.class);
        Root<UserEntity> from = query1.from(UserEntity.class);
        query1.select(from).where(cb.equal(from.get("login"), login));
        Query query2 = entityManager.createQuery(query1);
        List<UserEntity> resultList1 = query2.getResultList();
        if (resultList1.isEmpty()) return null;
        UserEntity userEntity = resultList1.get(0);
        return userEntity;
    }
}
