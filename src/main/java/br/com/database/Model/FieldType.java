package br.com.database.Model;

public enum FieldType {
    INTEGER,
    INT,
    TEXT,
    DATE,
    TIME,
    DATETIME,
    TIMESTAMP,
    FLOAT,
    DOUBLE,
    VARCHAR,
    CHAR;

    @Override
    public String toString() {
        switch (this) {
            case VARCHAR:
                return "VARCHAR(255)";
            case CHAR:
                return "CHAR(2)";
            default:
                return super.toString();
        }
    }

    /*
    public static String VARCHAR(int length) {
        return "VARCHAR(" + length + ")";
    }

    public static String CHAR(int length) {
        return "CHAR(" + length + ")";
    }
    */
}

