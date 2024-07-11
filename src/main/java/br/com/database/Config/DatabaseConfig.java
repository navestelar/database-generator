package br.com.database.Config;

public class DatabaseConfig {

    private String user = "root";
    private String host = "localhost";
    private String port = "3306";
    private String password;

    public DatabaseConfig(String password) {
        this.password =  password;
    }

    public DatabaseConfig user(String user) {
        this.user = user;
        return this;
    }

    public DatabaseConfig host(String host) {
        this.host = host;
        return this;
    }

    public DatabaseConfig port(String port) {
        this.port = port;
        return this;
    }

    public String getUrl(String driver) {
        return driver + "://" + host + ":" + port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "DatabaseConfig [user=" + user + ", host=" + host + ", port=" + port + ", password=" + password + "]";
    }
}
