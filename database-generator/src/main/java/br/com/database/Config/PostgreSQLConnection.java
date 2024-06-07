package br.com.database.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection implements DatabaseConnection {
    private final Connection connection;
    private static PostgreSQLConnection instance = null;

    public PostgreSQLConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DatabaseConfig.getUrl("jdbc:postgresql"), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error initializing PostgreSQL connection", e);
        }
    }

    public static PostgreSQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new PostgreSQLConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
