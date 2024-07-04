package br.com.database.Controller;

import br.com.database.Config.*;
import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.FieldType;
import br.com.database.Model.Table;


import java.sql.SQLException;

public class DatabaseManager {
    
    private DatabaseConfig databaseConfig;

    public DatabaseManager(DatabaseConfig config) {
        this.databaseConfig = config;
    }

    public Database createDatabase(String databaseName) {
        Database db = new Database(databaseName);
        return db;
    }

    public Table createTable(String tableName) {
        Table table = new Table(tableName);
        return table;
    }

    public Field createField(Table table, String name, FieldType type) {
        Field field = new Field(name, type);
        table.addField(field);
        return field;
    }

    public Field createField(Table table, String name, String type) {
        Field field = new Field(name, type);
        table.addField(field);
        return field;
    }

    public void generateScript(Database db) {
        ScriptGenerator.generateScript(db);
    }

    public void executeScricpt() throws SQLException {
        ScriptGenerator.executeScript(databaseConfig);

    }

}

