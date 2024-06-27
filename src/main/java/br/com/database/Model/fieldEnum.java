package br.com.database.Model;

public enum fieldEnum {
    INTEGER,
    INT,
    TEXT,
    DATE,
    TIME,
    DATETIME,
    TIMESTAMP,
    FLOAT,
    DOUBLE,
    VARCHAR {
        @Override
        public String toString() {
            return "VARCHAR";
        }
    },
    CHAR {
        @Override
        public String toString() {
            return "CHAR";
        }
    };

    public static fieldEnum VARCHAR(int length) {
        return new fieldEnum() {
            @Override
            public String toString() {
                return "VARCHAR(" + length + ")";
            }
        };
    }

    public static fieldEnum CHAR(int length) {
        return new fieldEnum() {
            @Override
            public String toString() {
                return "CHAR(" + length + ")";
            }
        };
    }
}
