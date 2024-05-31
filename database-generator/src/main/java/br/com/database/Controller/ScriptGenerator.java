package br.com.database.Controller;

import br.com.database.Model.Database;
import br.com.database.Model.Field;
import br.com.database.Model.Table;


import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class ScriptGenerator {

    String filePath = "/home/yohanes/Default/projetos.POO/databaseGenerator/database-generator/database-generator/src/main/java/br/com/database/Sql/script.sql";

    public String createDataBase(Database database) {
        return "CREATE DATABASE IF NOT EXISTS " + database.getName() + ";";
    }

    public String createTable(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(table.getName()).append(" (");

        for(Field field : table.getFields()) {
            sb.append(field.getName()).append(" ").append(field.getType()).append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append(");");
        return sb.toString();
    }

    public String createField(Table table, Field field) {
        return "ALTER TABLE " + table.getName() + " ADD " + field.getName() + " " + field.getType() + ";";
    }

    public String selectDataBase(Database database) {
        return "USE " + database.getName() + ";";
    }

    public void generateScript(Database database) {
        try (FileWriter writer = new FileWriter(filePath)) {
           
            writer.write(createDataBase(database) + "\n");
            writer.write(selectDataBase(database) + "\n");

            for (Table table : database.getTables()) {
                writer.write(createTable(table) + "\n");

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void executeScript(Connection connection) {
        try {
            String script = new String(Files.readAllBytes(Paths.get(filePath)));
            Statement statement = connection.createStatement();
            for (String sql : script.split(";")) {
                if (!sql.trim().isEmpty()) {
                    statement.execute(sql.trim() + ";");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}