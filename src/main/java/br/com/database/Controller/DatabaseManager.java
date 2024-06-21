package br.com.database.Controller;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Config.DatabaseConnection;
import br.com.database.Config.DatabaseConnectionFactory;
import br.com.database.Config.DatabaseType;
import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.Table;

import java.sql.SQLException;

public class DatabaseManager {

    public static void configureDadabase(DatabaseType databaseType,String user, String password, String host, String port) {
        DatabaseConfig.init(databaseType, user, password, host, port);
    }

    public static void createDatabase(String databaseName) {
        Database db = new Database(databaseName);
    }

    public static void createTable(String tableName) {
        Table table = new Table(tableName);
    }

    public static void createField(Table table, String name, String type) {
        Field field = new Field(name, type);
        table.addField(field);
    }

    public static void generateScript(Database db) {
        ScriptGenerator.generateScript(db);
    }

    public static void executeStricpt() throws SQLException {
        ScriptGenerator.executeScript(DatabaseConnectionFactory.getInstance().getConnection());
    }
}

