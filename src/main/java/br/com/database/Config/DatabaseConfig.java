package br.com.database.Config;

public class DatabaseConfig {
    private static DatabaseType databaseType;
    private static String user;
    private static String host;
    private static String port;
    private static String password;

    public static void init(DatabaseType databaseType, String user, String password, String host, String port) {
        DatabaseConfig.databaseType = databaseType;
        DatabaseConfig.user = user;
        DatabaseConfig.password = password;
        DatabaseConfig.host = host;
        DatabaseConfig.port = port;
    }

    public static String getUrl(String driver) {
        return driver + "://" + host + ":" + port;
    }

    public static DatabaseType getDatabaseType() {
        return databaseType;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }
}
