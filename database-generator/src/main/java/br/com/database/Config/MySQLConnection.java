package br.com.database.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DatabaseConnection {
    private final Connection connection;
    private static MySQLConnection instance = null;

    public MySQLConnection(DatabaseConfig databaseConfig) throws SQLException {
        try {
            Class.forName("org.mysql.Driver");
            connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUser(), databaseConfig.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error initializing MySQL connection", e);
        }
    }

    public static MySQLConnection getInstance(DatabaseConfig databaseConfig) throws SQLException {
        if (instance == null) {
            instance = new MySQLConnection(databaseConfig);
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
