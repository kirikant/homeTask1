package project.utils.transformers;

import project.dto.AuditUser;
import project.entity.AuditUserEntity;
import project.entity.UserEntity;
import project.utils.api.ITransformer;

import java.util.ArrayList;
import java.util.List;

public class AuditTransformer implements ITransformer<AuditUserEntity,AuditUser> {
    private static AuditTransformer transformer = new AuditTransformer();

    private AuditTransformer() {
    }

    public static AuditTransformer getTransformer() {
        return transformer;
    }

    public AuditUser entityToDto(AuditUserEntity auditUserEntity) {
        return new AuditUser(auditUserEntity.getId(),
                auditUserEntity.getDtCreate(),
                auditUserEntity.getText(),
                auditUserEntity.getUser(),
                auditUserEntity.getAuthor());
    }

    public AuditUserEntity dtoToEntity(AuditUser auditUser){
        return  new AuditUserEntity(auditUser.getDtCreate(),
                auditUser.getText(),
                auditUser.getUser(),
                auditUser.getAuthor());
    }

    public List<AuditUser> listEntityToListDto(List<AuditUserEntity> auditUserEntities){
        ArrayList<AuditUser> auditUsers = new ArrayList<>();
        for (AuditUserEntity auditUserEntity : auditUserEntities) {
            auditUsers.add(entityToDto(auditUserEntity));
        }
        return auditUsers;
    }

}
