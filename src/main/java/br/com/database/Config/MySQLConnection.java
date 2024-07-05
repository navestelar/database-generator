package br.com.database.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DatabaseConnection {

    private static MySQLConnection instance;
    private final Connection connection;
    private DatabaseConfig dbConfig;
    

    public MySQLConnection(DatabaseConfig dbConfig) throws SQLException {
        this.dbConfig = dbConfig;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbConfig.getUrl("jdbc:mysql"), dbConfig.getUser(), dbConfig.getPassword());

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error initializing MySQL connection: " + e.getMessage());
            e.printStackTrace(); // Imprime o stack trace completo para diagnóstico
            throw new SQLException("Error initializing MySQL connection", e);
        }
    }

    public static MySQLConnection getInstance(DatabaseConfig dbConfig) throws SQLException {
        if (instance == null) {
            instance = new MySQLConnection(dbConfig);
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
