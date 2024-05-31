package br.com.database.Controller;

import br.com.database.Config.DatabaseConnection;
import br.com.database.Model.Database;
import br.com.database.Model.Table;

public class DatabaseBuilder {

    DatabaseConnection connection = DatabaseConnection.getInstance();
    private Database database;

    public DatabaseBuilder addTable(Table table) {
        database.addTable(table);
        return this;
    }

    public Database getDatabase() {
        return database;
    }
    

    

    
}
