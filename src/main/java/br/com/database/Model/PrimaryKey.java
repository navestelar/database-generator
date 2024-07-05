package br.com.database.Model;

public class PrimaryKey extends Field {
    private String name;
    private String type;

    public PrimaryKey(String name, FieldType type) {
        super(name, type);
    }

    public PrimaryKey(String name, String type) {
        super(name, type);
    }
}
