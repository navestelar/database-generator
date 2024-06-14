package br.com.database.Controller;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Config.DatabaseConnection;
import br.com.database.Config.DatabaseConnectionFactory;
import br.com.database.Config.DatabaseType;
import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.Table;

import java.sql.SQLException;

public class DatabaseManager {

    public void configureDadabase(DatabaseType databaseType,String user, String password, String host, String port) {
        DatabaseConfig.init(databaseType, user, password, host, port);
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

    public void executeStricpt() throws SQLException {
        ScriptGenerator.executeScript(DatabaseConnectionFactory.getInstance().getConnection());
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConfig.init(DatabaseType.MYSQL, "root", "123456", "127.0.0.1", "3306");
        
        Database database = new Database("teste1");
        
        
        Table table1 = new Table("minhaTabela");
        table1.addField(new Field("id", "INT"));
        table1.addField(new Field("nome", "VARCHAR(255)"));
        
        database.addTable(table1);

        Table table2 = new Table("outraTabela");
        table2.addField(new Field("codigo", "INT"));
        table2.addField(new Field("descricao", "VARCHAR(255)"));

        table2.addField(new Field("descricao2", "VARCHAR(255)"));

        database.addTable(table2);

        ScriptGenerator.generateScript(database);
        
        ScriptGenerator.executeScript(DatabaseConnectionFactory.getInstance().getConnection());
        
    }
}

