package br.com.database.Config;

public class DataBaseConfig {

    private static String user;
    private static String password;
    private static String host;
    private static String port;
    private static String database;
    private static String url;

    public static void init(String user, String password, String host, String port) {
        DataBaseConfig.user = user;
        DataBaseConfig.password = password;
        DataBaseConfig.host = host;
        DataBaseConfig.port = port;
        DataBaseConfig.url = buildUrl();
    }


    public static String buildUrl() {

        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://");
        sb.append(host).append(":"); 
        sb.append(port); 

        return sb.toString();

    }


    public static String getUser() {
        return user;
    }


    public static void setUser(String user) {
        DataBaseConfig.user = user;
    }


    public static String getPassword() {
        return password;
    }


    public static void setPassword(String password) {
        DataBaseConfig.password = password;
    }


    public static String getHost() {
        return host;
    }


    public static void setHost(String host) {
        DataBaseConfig.host = host;
    }


    public static String getPort() {
        return port;
    }


    public static void setPort(String port) {
        DataBaseConfig.port = port;
    }


    public static String getDatabase() {
        return database;
    }


    public static void setDatabase(String database) {
        DataBaseConfig.database = database;
    }


    public static String getUrl() {
        return url;
    }


    public static void setUrl(String url) {
        DataBaseConfig.url = url;
    }

    
    
    
}
