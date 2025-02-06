package org.example.problem1;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.example.problem1.DatabaseProperties.DB_PASSWORD;
import static org.example.problem1.DatabaseProperties.DB_URL;
import static org.example.problem1.DatabaseProperties.DB_USER;

public class Main {
    public static void main(String[] args) {
        DatabaseUtil.createTableIfNotExists();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            for (int i = 0; i < 5; i++) {
                String serverId = "Server-" + i;
                Thread serverThread = new Thread(new Server(connection, serverId), serverId);
                serverThread.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
