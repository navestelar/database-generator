package br.com.database.Config;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {
    static DatabaseConnection getInstance(DatabaseConfig config) throws SQLException {
        return DatabaseConnection;
    }

    Connection getConnection() throws SQLException;
    void close() throws SQLException;
}
