package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    private String CREATEUSERSTABLE = "CREATE TABLE IF NOT EXISTS users(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastname VARCHAR(50), age TINYINT)";
    private String DROPUSERTABLE = "DROP TABLE IF EXISTS users";
    private String SAVEUSER = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
    private String REMOVEUSERBYID = "DELETE FROM users WHERE id = ?";
    private String GETALLUSERS = "SELECT * FROM users";
    private String CLEANUSERSTABLE = "DELETE FROM users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATEUSERSTABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROPUSERTABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement statement = connection.prepareStatement(SAVEUSER)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement statement = connection.prepareStatement(REMOVEUSERBYID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GETALLUSERS);
            while (resultSet.next()) {
               User user = new User();
               user.setId(resultSet.getLong("id"));
               user.setName(resultSet.getString("name"));
               user.setLastName(resultSet.getString("lastname"));
               user.setAge(resultSet.getByte("age"));
               list.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEANUSERSTABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
