package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static final Connection con = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate("CREATE TABLE  IF NOT EXISTS user " + " ( Id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.isClosed();
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(" DROP TABLE IF EXISTS user ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        con.isClosed();
    }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (PreparedStatement prStatement = con.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            prStatement.setString(1, name);
            prStatement.setString(2, lastName);
            prStatement.setByte(3, age);
            prStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.isClosed();
        }

    }

    public void removeUserById(long id) throws SQLException {

        try (PreparedStatement prStatement = con.prepareStatement("DELETE  FROM user WHERE  id = ? ")) {
            prStatement.setLong(1, id);
            prStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.isClosed();
        }


    }

    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        try (Statement statement = con.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM user");
            while (res.next()) {
                User user = new User(res.getString("name"), res.getString("lastName"), res.getByte("age"));
                user.setId(res.getLong("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.isClosed();
        }

        return users;
    }

    public void cleanUsersTable() throws SQLException {
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.isClosed();
        }

    }
}
