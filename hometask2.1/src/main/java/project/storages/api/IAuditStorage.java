package project.storages.api;

import project.entity.AuditUserEntity;
import project.entity.UserEntity;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.util.List;

public interface IAuditStorage<T>  {
     void addAudit(AuditUserEntity auditUserEntity,T t);
    List<AuditUserEntity> readAudit (UserEntity userEntity,T t);
}
