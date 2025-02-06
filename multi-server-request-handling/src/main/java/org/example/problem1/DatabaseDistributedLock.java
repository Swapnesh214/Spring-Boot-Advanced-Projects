package org.example.problem1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class DatabaseDistributedLock {
    private final Connection connection;
    private final String serverId;
    private static final String LOCK_NAME = "request_lock";
    private static final long LOCK_TIMEOUT = 3*60*1000; // 3 minutes in ms

    public DatabaseDistributedLock(Connection connection, String serverId) {
        this.connection = connection;
        this.serverId = serverId;
    }

    // Attempts to acquire the lock, returns true if successful
    public boolean acquireLock() {
        clearExpiredLocks();
        String sqlInsertQuery = "INSERT IGNORE INTO distributed_locks (lock_name, lock_owner, lock_acquired_at, expires_at) " +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlInsertQuery);
            statement.setString(1, LOCK_NAME);
            statement.setString(2, serverId);
            statement.setTimestamp(3, Timestamp.from(Instant.now()));
            statement.setTimestamp(4, Timestamp.from(Instant.now().plusMillis(LOCK_TIMEOUT)));
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Returns true if lock was acquired
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Releases the lock
    public void releaseLock() throws SQLException {
        String sqlDeleteQuery = "DELETE FROM distributed_locks WHERE lock_name = ? AND lock_owner = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlDeleteQuery)) {
            statement.setString(1, LOCK_NAME);
            statement.setString(2, serverId);
            statement.executeUpdate();
            System.out.println(serverId + " released lock.");
        }
    }

    // clear any expired locks after timeout (when a server fails while holding the lock)
    private void clearExpiredLocks() {
        String sqlClearQuery = "DELETE FROM distributed_locks WHERE lock_name = ? AND expires_at < ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlClearQuery);
            statement.setString(1, LOCK_NAME);
            statement.setTimestamp(2, Timestamp.from(Instant.now()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
