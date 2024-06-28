package br.com.database;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Config.DatabaseConnectionFactory;
import br.com.database.Config.DatabaseType;
import br.com.database.Controller.ScriptGenerator;
import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.FieldType;
import br.com.database.Model.Table;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConfig.init(DatabaseType.MYSQL, "root", "1234", "localhost", "3306");

        Database database = new Database("teste");

        Table table1 = new Table("tabela1");
        Field pk = new Field("id", FieldType.INT);
        table1.addField(new Field("nome", FieldType.VARCHAR(20)));
        table1.addPrimaryKey(pk);
        table1.addPrimaryKey(new Field("pk", FieldType.INT));
        Field teste = new Field("teste",FieldType.VARCHAR(20));

        database.addTable(table1);

        Table table2 = new Table("tabela2") ;
        table2.addField(new Field("codigo", FieldType.INT));
        table2.addField(new Field("descricao", FieldType.VARCHAR(20)));

        table2.addField(new Field("descricao2", FieldType.VARCHAR(20)));

        database.addTable(table2);

        ScriptGenerator.generateScript(database);
        ScriptGenerator.executeScript(DatabaseConnectionFactory.getInstance().getConnection());
    }

}