package br.com.database;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Config.DatabaseConnectionFactory;
import br.com.database.Config.DatabaseType;
import br.com.database.Controller.ScriptGenerator;
import br.com.database.Model.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConfig.init(DatabaseType.MYSQL, "root", "1234", "localhost", "3306");

        Database database = new Database("teste");

        Table table1 = new Table("tabela1");
        PK pk = new PK("id", FieldType.INT);
        table1.addField(new Field("nome", FieldType.VARCHAR));
        table1.addPrimaryKey(pk);
        Field teste = new Field("teste",FieldType.VARCHAR);

        database.addTable(table1);

        Table table2 = new Table("tabela2");
        Field field2 = new Field("codigo", FieldType.INT);
        table2.addField(field2);
        table2.addField(new Field("descricao", FieldType.VARCHAR));
        table2.addPrimaryKey(new PK("pk", FieldType.INT));

        table2.addField(new Field("descricao2", FieldType.VARCHAR));

        database.addTable(table2);
        table2.addFk("fk", table1, pk);

        database.addTabelaAssociativa(new TabelaAssociativa("teste", table1, table2));

        ScriptGenerator.generateScript(database);
        ScriptGenerator.executeScript(DatabaseConnectionFactory.getInstance().getConnection());
    }

}