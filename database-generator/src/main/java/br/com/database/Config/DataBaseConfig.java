package br.com.database.Config;

public class DatabaseConfig {
    private final DatabaseType databaseType;
    private final String user;
    private final String host;
    private final String port;
    private final String password;

    public DatabaseConfig(DatabaseType databaseType, String user, String password, String host, String port) {
        this.databaseType = databaseType;
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public String getUrl() {
        return "jdbc:" + databaseType.toString() + "://" + host + ":" + port;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
