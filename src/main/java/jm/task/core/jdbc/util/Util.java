package jm.task.core.jdbc.util;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/testy";
    private static final String USER_NAME = "rot";
    private static final String PASSWORD = "root";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println(" Соеденение установлено");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Соеденение не установлено");
        }
        return connection;
    }
}









