package br.com.database.Controller;

import br.com.database.Config.DatabaseConnection;
import br.com.database.Model.Table;
import br.com.database.Model.Field;

public class TableBuilder {

    DatabaseConnection connection = DatabaseConnection.getInstance();
    private Table table;

    public TableBuilder addField(Field field) {
        table.addField(field);
        return this;
    }

    public Table getTable() {
        return table;
    }

    
}
