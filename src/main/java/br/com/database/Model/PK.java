package br.com.database.Model;

public class PK extends Field {
    private String name;
    private FieldType type;

    public PK(String name, FieldType type) {
        super(name, type);
    }
}
