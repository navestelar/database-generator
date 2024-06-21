package br.com.database.Controller;

import br.com.database.Model.Field;

public class FieldBuilder {

    private Field field;

    public FieldBuilder(String fieldName, String fieldType) {
        field = new Field(fieldName, fieldType);
    }

    public Field getField() {
        return field;
    }
    
}
