package fr.epsi.jeeProject.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresServer {
    public static Connection connection;

    private void initialize() {
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://51.158.111.171:5432/jee", "profjee", "Passw0rdProfJEE");
        return connection;
    }

    public PostgresServer() {
        initialize();
    }
}