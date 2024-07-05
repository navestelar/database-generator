package br.com.database.Model;

public class PrimaryKey extends Field {
    private String name;
    private FieldType type;

    public PrimaryKey(String name, FieldType type) {
        super(name, type);
    }
}
