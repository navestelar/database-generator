package br.com.database.Controller;

import br.com.database.Config.DatabaseConfig;
import br.com.database.Config.MySQLConnection;
import br.com.database.Model.*;

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

        if (table != null && !table.getFields().isEmpty()) {
            sb.append("CREATE TABLE IF NOT EXISTS ").append(table.getName()).append(" (");

            for (Field field : table.getFields()) {
                sb.append(createField(field));
            }

            sb.append(createPrimaryKey(table));

            sb.setLength(sb.length() - 2);
            sb.append(");");
        }

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

        List<ForeignKey> foreignKeyList = table.getFks();

        if (foreignKeyList != null) {
            for (ForeignKey foreignKey : foreignKeyList) {
                Field pkTable1 = foreignKey.getTable().getPrimaryKey();
                Field fieldFk = foreignKey.getTable().getPrimaryKey();

                if (pkTable1 != null && fieldFk != null && pkTable1.getName().equals(fieldFk.getName())) {
                    sb.append(updateField(table, new Field(foreignKey.getName(), foreignKey.getField().getType())) + "\n");
                    sb.append("ALTER TABLE ").append(table.getName())
                            .append(" ADD CONSTRAINT ").append(foreignKey.getName()).append(" ")
                            .append(addForeignKey(foreignKey.getName(), foreignKey.getTable().getName(), foreignKey.getField().getName()))
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

        Field pk1 = tabela1.getPrimaryKey();
        Field pk2 = tabela2.getPrimaryKey();

        if (pk1 != null && pk2 != null) {
            Field fk1 = new Field(tabela1.getName() + "_" + pk1.getName(), pk1.getType());
            Field fk2 = new Field(tabela2.getName() + "_" + pk2.getName(), pk2.getType());

            sb.append("CREATE TABLE IF NOT EXISTS ").append(name).append(" (\n")
                    .append(createField(fk1)).append("\n")
                    .append(createField(fk2)).append("\n")
                    .append(addForeignKey(fk1.getName(), tabela1.getName(), pk1.getName())).append(",\n")
                    .append(addForeignKey(fk2.getName(), tabela2.getName(), pk2.getName())).append(",\n")
                    .append("PRIMARY KEY (").append(fk1.getName()).append(", ").append(fk2.getName()).append(")\n")
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