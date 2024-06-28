package br.com.database.Controller;

import br.com.database.Config.DatabaseConnection;
import br.com.database.Config.DatabaseConnectionFactory;
import br.com.database.Model.Table;
import br.com.database.Model.Field;

import java.sql.SQLException;

public class TableBuilder {

    DatabaseConnection connection = DatabaseConnectionFactory.getInstance();
    private Table table;

    public TableBuilder() throws SQLException {
    }

    public TableBuilder addField(Field field) {
        table.addField(field);
        return this;
    }

    public Table getTable() {
        return table;
    }

    
}
