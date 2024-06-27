package br.com.database.Config;

import java.sql.SQLException;

public class DatabaseConnectionFactory {
    public static DatabaseConnection setDatabaseConnection(DatabaseConfig config) throws SQLException {
        switch (config.getDatabaseType()) {
            case POSTGRESQL:
                return PostgreSQLConnection.getInstance(config);
            case MYSQL:
                return MySQLConnection.getInstance(config);
        }
        return null;
    }
}
