package br.com.database.Controller;

import br.com.database.Model.Database;
import br.com.database.Model.Table;

import java.sql.SQLException;

public class DatabaseBuilder {

    private Database database;

    public DatabaseBuilder() throws SQLException {
    }

    public DatabaseBuilder addTable(Table table) {
        database.addTable(table);
        return this;
    }

    public Database getDatabase() {
        return database;
    }

}
