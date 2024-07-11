package br.com.database;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Controller.DatabaseManager;
import br.com.database.Model.FieldType;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //configurando database
        DatabaseConfig config = new DatabaseConfig("1234").user("root");
        DatabaseManager manager = new DatabaseManager(config);

        //criando database
        manager.createDatabase("teste2");

        //criando tabela
        manager.createTable("tabela1");

        //criando campo
        manager.addField("tabela1", "id", FieldType.INT)
                .addField("tabela1", "campo1", FieldType.VARCHAR(20))
                .addField("tabela1", "campo2", FieldType.VARCHAR(23))
                .addField("tabela1", "campo3", FieldType.CHAR(2));

        //adicionando primary key
        manager.addPrimaryKey("tabela1", "pk1", FieldType.VARCHAR(20));

        manager.createTable("tabela2")
                .addField("tabela2", "campo4", FieldType.CHAR(2))
                .addPrimaryKey("tabela2", "pk2", FieldType.INT);

        //adicionando foreign key
        manager.addForeignKey("tabela1", "tabela2", "fk", "pk");

        //criando tabela associativa
        manager.createTabelaAssociativa("tabela1_tabela2", "tabela1", "tabela2");

        //gerando e executando o script
        manager.generateScript();
        manager.executeScript();
        
    }

}