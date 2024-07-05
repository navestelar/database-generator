package br.com.database.Model;

public class TabelaAssociativa {
    private String name;
    private Table tabela1;
    private Table tabela2;

    public TabelaAssociativa(String name, Table tabela1, Table tabela2) {
        this.name = name;
        this.tabela1 = tabela1;
        this.tabela2 = tabela2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Table getTabela1() {
        return tabela1;
    }

    public void setTabela1(Table tabela1) {
        this.tabela1 = tabela1;
    }

    public Table getTabela2() {
        return tabela2;
    }

    public void setTabela2(Table tabela2) {
        this.tabela2 = tabela2;
    }
}
