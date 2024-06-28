package br.com.database.Model;

public class FK {
    private String name;
    private Table table;
    private Field field;

    public FK(String name, Table table, Field field) {
        this.name = name;
        this.table = table;
        this.field = field;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
