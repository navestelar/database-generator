package br.com.database.Controller;

import br.com.database.Config.*;
import br.com.database.Model.*;


import java.sql.SQLException;

public class DatabaseManager {
    
    private final DatabaseConfig databaseConfig;
    private Database database;

    public DatabaseManager(DatabaseConfig config) {
        this.databaseConfig = config;
    }

    public void createDatabase(String databaseName) {
        this.database = new Database(databaseName);
    }

    public DatabaseManager createTable(String tableName) {
        Table table = new Table(tableName);

        if (database.getTable(tableName) == null) {
            database.addTable(table);
        }

        return this;
    }

    public DatabaseManager addField(String tableName, String name, FieldType type) {
        Field field = new Field(name, type);
        Table table = database.getTable(tableName);

        if (table != null) {
            table.addField(field);
        }

        return this;
    }

    public DatabaseManager addField(String tableName, String name, String type) {
        Field field = new Field(name, type);
        Table table = database.getTable(tableName);

        if (table != null) {
            table.addField(field);
        }

        return this;
    }

    public DatabaseManager addPrimaryKey(String tableName, String name, FieldType type) {
        PrimaryKey primaryKey = new PrimaryKey(name, type);
        Table table = database.getTable(tableName);

        if (table != null) {
            table.addPrimaryKey(primaryKey);
        }

        return this;
    }

    public DatabaseManager addPrimaryKey(String tableName, String name, String type) {
        PrimaryKey primaryKey = new PrimaryKey(name, type);
        Table table = database.getTable(tableName);

        if (table != null) {
            table.addPrimaryKey(primaryKey);
        }

        return this;
    }

    public DatabaseManager addForeignKey(String tableName1, String tableName2, String name, String fieldName) {
        Table table1 = database.getTable(tableName1);
        Table table2 = database.getTable(tableName2);

        if (table1 != null && table2 != null) {
            PrimaryKey primaryKey = table2.getPrimaryKey();

            if (primaryKey.getName().equals(fieldName)) {
                table1.addForeignKey(name, table2, primaryKey);
            }
        }

        return this;
    }

    public DatabaseManager createTabelaAssociativa(String name, String tableName1, String tableName2) {
        Table table1 = database.getTable(tableName1);
        Table table2 = database.getTable(tableName2);

        if (table1 != null && table2 != null) {
            database.addTabelaAssociativa(name, table1, table2);
        }

        return this;
    }

    public void generateScript() {
        ScriptGenerator.generateScript(database);
    }

    public void executeScript() throws SQLException {
        ScriptGenerator.executeScript(databaseConfig);
    }
}