package br.com.database.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DatabaseConnection {
    private final Connection connection;
    private static MySQLConnection instance;

    public MySQLConnection() throws SQLException {
        try {
            Class.forName("org.mysql.Driver");
            connection = DriverManager.getConnection(DatabaseConfig.getUrl("jdbc:mysql"), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error initializing MySQL connection", e);
        }
    }

    public static MySQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySQLConnection();
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
