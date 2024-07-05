package br.com.database.Model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private String name;
    private List<Table> tables = new ArrayList<>();
    private List<TabelaAssociativa> tabelaAssociativas = new ArrayList<>();

    public boolean containsTable(Table table) {
        return tables.contains(table);
    }

    public Database(String name) {
        this.name = name;
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void addTabelaAssociativa(TabelaAssociativa tabelaAssociativa) {
        tabelaAssociativas.add(tabelaAssociativa);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Table> getTables() {
        return tables;
    }

    public List<TabelaAssociativa> getTabelaAssociativas() {
        return tabelaAssociativas;
    }

    public void setTabelaAssociativas(List<TabelaAssociativa> tabelaAssociativas) {
        this.tabelaAssociativas = tabelaAssociativas;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    
    
}
