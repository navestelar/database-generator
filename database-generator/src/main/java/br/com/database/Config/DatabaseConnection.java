package br.com.database.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection conn;

    private DatabaseConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = DataBaseConfig.getUrl();
            this.conn = DriverManager.getConnection(url, DataBaseConfig.getUser(), DataBaseConfig.getPassword());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance(){ 

        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void closeDataSource() throws SQLException {
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }
}
