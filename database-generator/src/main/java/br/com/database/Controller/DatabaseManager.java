package br.com.database.Controller;

import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.Table;

public class DatabaseManager {

    public void configureDadabase(String user, String password, String host, String port) {
        DataBaseConfig.init(user, password, host, port);
    }

    public void createDatabase(String databaseName) {
        Database db = new Database(databaseName);
    }

    public void createTable(String tableName) {
        Table table = new Table(tableName);
    }


    public void createField(Table table, String fieldName, String fieldType) {
        Field field = new Field(fieldName, fieldType);
        table.addField(field);
    }

    public void generateScript(Database db) {
        ScriptGenerator.generateScript(db);
    }

    public void executeStricpt() {
        ScriptGenerator.executeScript(DatabaseConnection.getInstance().getConnection());
    }

    public static void main(String[] args) {
        
        
        DataBaseConfig.init("root", "1234", "localhost", "3306");
        
        Database database = new Database("meuBanco");
        
        
        Table table1 = new Table("minhaTabela");
        table1.addField(new Field("id", "INT"));
        table1.addField(new Field("nome", "VARCHAR(255)"));
        
        database.addTable(table1);

        Table table2 = new Table("outraTabela");
        table2.addField(new Field("codigo", "INT"));
        table2.addField(new Field("descricao", "VARCHAR(255)"));
        
        database.addTable(table2);
        
        
        ScriptGenerator scriptGenerator = new ScriptGenerator();
        scriptGenerator.generateScript(database);
        
        scriptGenerator.executeScript(DatabaseConnection.getInstance().getConnection());
        
    }
}

