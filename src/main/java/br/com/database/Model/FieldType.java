package br.com.database.Model;

  

public enum FieldType {

    INTEGER,
    INT;

    public static FieldType VARCHAR(int length) {
        return "VARCHAR(" + length + ")";
    } 

    public static FieldType CHAR(int length) {
        return "CHAR(" + length + ")";
    }

}