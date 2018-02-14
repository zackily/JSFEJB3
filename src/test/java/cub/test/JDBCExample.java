package cub.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCExample {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/INVINST", "postgres",
                "postgres");
            ResultSet rs = connection.createStatement().executeQuery(
                "SELECT VERSION()");
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}
