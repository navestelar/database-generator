package br.com.database;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Controller.DatabaseManager;
import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.FieldType;
import br.com.database.Model.Table;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DatabaseConfig config = new DatabaseConfig("1234").user("root");
        DatabaseManager manager = new DatabaseManager(config);
        //configurando database
        
        //criando database
        Database database = manager.createDatabase("teste");

        //criando tabela
        Table table = manager.createTable("tabela1");

        //criando campo
        Field field = manager.createField(table, "id", FieldType.INT);
        manager.createField(table, "campo1", FieldType.VARCHAR(20));
        manager.createField(table, "campo2", FieldType.VARCHAR(23));
        manager.createField(table, "campo3", FieldType.CHAR(2));
        
        //adicionando primary key
        table.addPrimaryKey(field);

        //adicionando tabela ao database
        database.addTable(table);

        //gerando e executando o script
        manager.generateScript(database);
        manager.executeScript();
        
    }

}