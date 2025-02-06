package org.example.problem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.problem1.DatabaseProperties.DB_PASSWORD;
import static org.example.problem1.DatabaseProperties.DB_URL;
import static org.example.problem1.DatabaseProperties.DB_USER;

public class DatabaseUtil {
    private DatabaseUtil() {}

    public static void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS distributed_locks (\n" +
                "                lock_name VARCHAR(255) NOT NULL PRIMARY KEY,\n" +
                "                lock_owner VARCHAR(255),\n" +
                "                lock_acquired_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                "                expires_at TIMESTAMP\n" +
                "            );";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'distributed_locks' is ready.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}
