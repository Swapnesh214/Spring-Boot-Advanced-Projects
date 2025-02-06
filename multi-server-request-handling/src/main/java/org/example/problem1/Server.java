package org.example.problem1;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable{
    private final DatabaseDistributedLock lock;
    private final String serverId;
    public Server(Connection connection, String serverId) {
        this.lock = new DatabaseDistributedLock(connection, serverId);
        this.serverId = serverId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (lock.acquireLock()) {
                    try {
                        handleRequest();   // Process the request while holding the lock
                    } finally {
                        lock.releaseLock();  // Exit the loop after handling the request
                    }
                    break;
                } else {
                    System.out.println(serverId + " could not acquire lock. Retrying...");
                    TimeUnit.SECONDS.sleep(2); // Wait before retrying
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleRequest() {
        try {
            System.out.println(serverId + " is processing request...");
            Thread.sleep(10000); // Simulate time taken to handle request
            System.out.println(serverId + " finished processing request.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
