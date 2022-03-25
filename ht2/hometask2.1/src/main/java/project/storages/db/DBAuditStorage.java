package project.storages.db;

import project.entity.AuditUserEntity;
import project.storages.api.IAuditStorage;
import project.utils.DBInitializer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAuditStorage implements IAuditStorage {
    private static DBAuditStorage databaseDBStorage = new DBAuditStorage();
    public static DBAuditStorage getDatabaseAuditStorage() {
        return databaseDBStorage;
    }
    private DataSource dataSource;

    private DBAuditStorage() {
        dataSource = DBInitializer.getInstance().getDataSource();
    }

    public void addAudit(AuditUserEntity auditUserEntity){
        long id = 0;
        try (PreparedStatement preparedStatement1 = dataSource.getConnection()
                .prepareStatement(DBAuditStorage.Commands.GET_USER.value)){
            preparedStatement1.setString(1,auditUserEntity.getUser());
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()) id=resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(Commands.ADD_AUDIT_USER.value)) {
            preparedStatement.setObject(1, auditUserEntity.getAuthor());
            preparedStatement.setObject(2, auditUserEntity.getDtCreate());
            preparedStatement.setObject(3, auditUserEntity.getText());
            preparedStatement.setString(4, auditUserEntity.getUser());
            preparedStatement.setLong(5,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AuditUserEntity> readAudit (String login){
        List<AuditUserEntity> auditUserEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(Commands.GET_USER_AUDITS.value)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                   auditUserEntities.add(new AuditUserEntity(resultSet.getLong(1),
                            ((java.sql.Timestamp) (resultSet.getObject(3)))
                                    .toLocalDateTime(),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auditUserEntities;
    }

    private enum Commands {
        ADD_AUDIT_USER("INSERT INTO audits VALUES (default,?,?,?,?,?)"),
        GET_USER("SELECT * FROM users WHERE login=?"),
        GET_USER_AUDITS("SELECT * FROM audits WHERE useraudit=?");

        String value;

        Commands(String value) {
            this.value = value;
        }
    }
}
