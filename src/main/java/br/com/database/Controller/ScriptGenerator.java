package br.com.database.Controller;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Config.MySQLConnection;
import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScriptGenerator {
    static String filePath = "script.sql";

    public static String createDataBase(Database database) {
        return "CREATE DATABASE IF NOT EXISTS " + database.getName() + ";";
    }

    public static String createField(Field field) {
        return field.getName() + " " + field.getType() + ", ";
    }

    private static String addPrimaryKey(String primaryKey) {
        return "PRIMARY KEY (" + primaryKey + "), ";
    }

    private static String addForeignKey(String name, String table, String field) {
        return "FOREIGN KEY (" + name + ")" + " REFERENCES " + table + " (" + field + ")";
    }

    public static String createPrimaryKey(Table table) {
        StringBuilder sb = new StringBuilder();
        Field pk = table.getPrimaryKey();

        if (table.getPrimaryKey() != null) {
            boolean tableHasPrimaryKey = table.getFields().contains(pk);

            if (!tableHasPrimaryKey) {
                sb.append(createField(pk));
            }

            sb.append(addPrimaryKey(pk.getName()));
        }

        return sb.toString();
    }

    public static String createTable(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(table.getName()).append(" (");

        for (Field field : table.getFields()) {
            sb.append(createField(field));
        }

        sb.append(createPrimaryKey(table));

        sb.setLength(sb.length() - 2);
        sb.append(");");
        return sb.toString();
    }

    public static String updateField(Table table, Field field) {
        return "ALTER TABLE " + table.getName() + " ADD " + field.getName() + " " + field.getType() + ";";
    }

    public static String selectDataBase(Database database) {
        return "USE " + database.getName() + ";";
    }

    private static String createFk(Table table) {
        StringBuilder sb = new StringBuilder();

        List<FK> fkList = table.getFks();

        if (fkList != null) {
            for (FK fk : fkList) {
                Field pkTable1 = fk.getTable().getPrimaryKey();
                Field fieldFk = fk.getTable().getPrimaryKey();

                if (pkTable1 != null && fieldFk != null && pkTable1.getName().equals(fieldFk.getName())) {
                    sb.append(updateField(table, new Field(fk.getName(), fk.getField().getType())) + "\n");
                    sb.append("ALTER TABLE ").append(table.getName())
                            .append(" ADD CONSTRAINT ").append(fk.getName()).append(" ")
                            .append(addForeignKey(fk.getName(), fk.getTable().getName(), fk.getField().getName()))
                            .append(";");
                } else {
                    System.out.println("O field deve ser uma pk");
                }
            }
        }

        return sb.toString();
    }

    private static String createTabelaAssociativa(String name, Table tabela1, Table tabela2, Database database) {
        StringBuilder sb = new StringBuilder();

        if (!database.containsTable(tabela1)) {
            sb.append(createTable(tabela1));
        }

        if (!database.containsTable(tabela2)) {
            sb.append(createTable(tabela2));
        }

        PK pk1 = tabela1.getPrimaryKey();
        PK pk2 = tabela2.getPrimaryKey();

        if (pk1 != null && pk2 != null) {
            sb.append("CREATE TABLE IF NOT EXISTS ").append(name).append(" (\n")
                    .append(createField(pk1)).append("\n")
                    .append(createField(pk2)).append("\n")
                    .append(addForeignKey(pk1.getName(), tabela1.getName(), pk1.getName())).append(",\n")
                    .append(addForeignKey(pk2.getName(), tabela2.getName(), pk2.getName())).append(",\n")
                    .append("PRIMARY KEY (").append(pk1.getName()).append(", ").append(pk2.getName()).append(")\n")
                    .append(");");
        } else {
            System.out.println("Não foi possível criar tabela associativa pois uma das tabelas não possuem pk.");
        }

        return sb.toString();
    }

    public static void generateScript(Database database) {
        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write(createDataBase(database) + "\n");
            writer.write(selectDataBase(database) + "\n");

            for (Table table : database.getTables()) {
                writer.write(createTable(table) + "\n");
            }

            for (Table table : database.getTables()) {
                writer.write(createFk(table) + "\n");
            }

            for (TabelaAssociativa tabelaAssociativa : database.getTabelaAssociativas()) {
                writer.write(createTabelaAssociativa(tabelaAssociativa.getName(), tabelaAssociativa.getTabela1(),
                        tabelaAssociativa.getTabela2(), database));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void executeScript(DatabaseConfig dbconfig) {
        try {
            String script = new String(Files.readAllBytes(Paths.get(filePath)));
            Statement statement = MySQLConnection.getInstance(dbconfig).getConnection().createStatement();
            for (String sql : script.split(";")) {
                if (!sql.trim().isEmpty()) {
                    statement.execute(sql.trim() + ";");
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}