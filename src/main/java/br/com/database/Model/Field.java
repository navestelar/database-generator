package br.com.database.Model;

public class Field {

    private String name;
    private String type;

    public Field(String name, FieldType type) {
        this.name = name;
        this.type = type.toString();
    }

    

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }

    
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
    }


    

    

}
