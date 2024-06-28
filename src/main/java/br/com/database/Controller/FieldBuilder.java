package br.com.database.Controller;

import br.com.database.Model.Field;
import br.com.database.Model.FieldType;

public class FieldBuilder {

    private Field field;

    public FieldBuilder(String fieldName, FieldType type) {
        field = new Field(fieldName, type); 
    }
        
          
        
    public Field getField() {
        return field;
    }
    
}
