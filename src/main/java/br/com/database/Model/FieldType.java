package br.com.database.Model;

  

public enum FieldType {

    INTEGER,
    INT;

    public static String VARCHAR(int length) {
        return "VARCHAR(" + length + ")";
    } 

    public static String CHAR(int length) {
        return "CHAR(" + length + ")";
    }

}