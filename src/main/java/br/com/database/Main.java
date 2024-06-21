package br.com.database;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Config.DatabaseConnectionFactory;
import br.com.database.Config.DatabaseType;
import br.com.database.Controller.ScriptGenerator;
import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.Table;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConfig.init(DatabaseType.MYSQL, "root", "1234", "localhost", "3306");

        Database database = new Database("teste");

        Table table1 = new Table("tabela1");
        Field pk = new Field("id", "INT");
        table1.addField(new Field("nome", "VARCHAR(255)"));
        table1.addPrimaryKey(pk);
        table1.addPrimaryKey(new Field("pk", "INT"));

        database.addTable(table1);

        Table table2 = new Table("tabela2") ;
        table2.addField(new Field("codigo", "INT"));
        table2.addField(new Field("descricao", "VARCHAR(255)"));

        table2.addField(new Field("descricao2", "VARCHAR(255)"));

        database.addTable(table2);

        ScriptGenerator.generateScript(database);
        ScriptGenerator.executeScript(DatabaseConnectionFactory.getInstance().getConnection());
    }

}