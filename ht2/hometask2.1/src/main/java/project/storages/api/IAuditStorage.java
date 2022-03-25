package project.storages.api;

import project.entity.AuditUserEntity;

import java.util.List;

public interface IAuditStorage  {
     void addAudit(AuditUserEntity auditUserEntity);
    List<AuditUserEntity> readAudit (String login);
}
