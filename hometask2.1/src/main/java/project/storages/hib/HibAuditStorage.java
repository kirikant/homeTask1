package project.storages.hib;

import project.entity.AuditUserEntity;
import project.entity.UserEntity;
import project.storages.api.IAuditStorage;
import project.utils.EntityFactoryInitializer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibAuditStorage implements IAuditStorage<EntityManager> {
    private EntityFactoryInitializer factoryInitializer;

    private static HibAuditStorage hibAuditStorage = new HibAuditStorage();

    public static HibAuditStorage getHibAuditStorage() {
        return hibAuditStorage;
    }

    private HibAuditStorage() {
        factoryInitializer = EntityFactoryInitializer.getInstance();
    }


    public void addAudit(AuditUserEntity auditUserEntity,EntityManager entityManager){
        entityManager.persist(auditUserEntity);
    }

    public List<AuditUserEntity> readAudit (UserEntity userEntity,EntityManager entityManager){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditUserEntity> query = cb.createQuery(AuditUserEntity.class);
        Root<AuditUserEntity> from = query.from(AuditUserEntity.class);
        CriteriaQuery<AuditUserEntity> create = query.select(from)
                .where(cb.equal(from.get("userAudit"),userEntity))
                .orderBy(cb.asc(from.get("dtCreate")));
        TypedQuery<AuditUserEntity> query1 = entityManager.createQuery(create);
        List<AuditUserEntity> resultList = query1.getResultList();
        return  resultList;
    }



}
