package br.com.database.Model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<Field> fields = new ArrayList<>();
    private Field primaryKey;


    public Table(String name) {
        this.name = name;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addPrimaryKey(Field field) {
        primaryKey = field;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Field> getFields() {
        return fields;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Field getPrimaryKey() {
        return primaryKey;
    }
}
