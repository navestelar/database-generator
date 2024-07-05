package br.com.database.Model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<Field> fields = new ArrayList<>();
    private PrimaryKey primaryKey;
    private List<ForeignKey> foreignKeys = new ArrayList<>();

    public Table(String name) {
        this.name = name;
    }

    public Field getField(String fieldName) {
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        return null;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addForeignKey(String name, Table table, Field field) {
        ForeignKey foreignKey = new ForeignKey(name, table, field);
        foreignKeys.add(foreignKey);
    }

    public void addPrimaryKey(PrimaryKey field) {
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
    public List<ForeignKey> getFks() {
        return foreignKeys;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }
}
