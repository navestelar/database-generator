package br.com.database.Config;

import java.sql.SQLException;

public class DatabaseConnectionFactory {
    public static DatabaseConnection getInstance() throws SQLException {
        switch (DatabaseConfig.getDatabaseType()) {
            case POSTGRESQL:
                return PostgreSQLConnection.getInstance();
            case MYSQL:
                return MySQLConnection.getInstance();
        }
        return null;
    }
}
