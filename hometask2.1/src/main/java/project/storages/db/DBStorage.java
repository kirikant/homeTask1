package project.storages.db;

import project.dto.Pageable;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.storages.api.IStorage;
import project.utils.DBInitializer;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStorage implements IStorage<Connection> {
    private static DBStorage databaseDBStorage = new DBStorage();

    public static DBStorage getDatabaseStorage() {
        return databaseDBStorage;
    }

    private DataSource dataSource;

    private DBStorage() {
        dataSource = DBInitializer.getInstance().getDataSource();
    }

    public void addUser(UserEntity user, Connection connection) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Commands.ADD_USER.value)) {
            preparedStatement.setObject(1, user.getBirthDay());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addMessage(MessageEntity message, Connection connection) {
        long id = 0;
        try (PreparedStatement preparedStatement1 = connection
                .prepareStatement(Commands.GET_USER.value)) {
            preparedStatement1.setString(1, message.getLoginReceiver().getLogin());
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()) id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(Commands.ADD_MESSAGE.value)) {
            preparedStatement.setObject(1, message.getDateTime());
            preparedStatement.setObject(2, message.getLoginSender());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setLong(4, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public long getUsersNumber() {
        int result = 0;
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(Commands.GET_LOGINS_NUMBER.value);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public long getMessagesNumber() {
        int result = 0;
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(Commands.GET_MESSAGES_NUMBER.value);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean haveUserExistence(String login) {
        boolean result = false;
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(Commands.GET_USER_EXISTENCE.value)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getInt(1) > 0) result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserEntity getUser(String login, Connection connection) {
        UserEntity user = null;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Commands.GET_USER.value)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserEntity(resultSet.getLong(1),
                            resultSet.getObject(2) != null ?
                                    resultSet.getObject(2).toString() : null,
                            resultSet.getString(3),
                            resultSet.getString(4) != null ?
                                    resultSet.getString(4) : null,
                            resultSet.getString(5));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<MessageEntity> getUserMessages(UserEntity userEntity, Pageable pageable) {
        List<MessageEntity> messages = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(Commands.GET_USER_MESSAGES.value)) {
            preparedStatement.setLong(1, databaseDBStorage
                    .getUser(userEntity.getLogin(), connection).getId());
            preparedStatement.setInt(2,pageable.getAmount());
            preparedStatement
                    .setInt(3,pageable.getAmount()*(pageable.getPage())-1);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    messages.add(new MessageEntity(resultSet.getLong(1),
                            ((java.sql.Timestamp) (resultSet.getObject(2)))
                                    .toLocalDateTime(),
                            userEntity,
                            resultSet.getString(3),
                            resultSet.getString(4)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public boolean checkPassword(String login, String password) {
        boolean result = false;
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(Commands.CHECK_PASSWORD.value)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (password.equals(resultSet.getString(1))) {
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private enum Commands {
        ADD_MESSAGE("INSERT INTO messages VALUES (nextval('hibernate_sequence'),?,?,?,?)"),
        ADD_USER("INSERT INTO users VALUES (nextval('hibernate_sequence'),?,?,?,?)"),
        GET_LOGINS_NUMBER("SELECT count(login) FROM users"),
        GET_MESSAGES_NUMBER("SELECT count(*) FROM messages"),
        GET_USER_EXISTENCE("SELECT count(*) FROM users WHERE login =?"),
        GET_USER("SELECT * FROM users WHERE login=?"),
        GET_USER_MESSAGES("SELECT * FROM messages WHERE loginreceiver_id=? limit ? offset ? "),
        CHECK_PASSWORD("SELECT password FROM users WHERE login=?");
        String value;

        Commands(String value) {
            this.value = value;
        }
    }
}