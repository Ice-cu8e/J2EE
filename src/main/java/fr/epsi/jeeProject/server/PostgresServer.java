package fr.epsi.jeeProject.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresServer {
    static public Connection dbConn;

    static public Connection getConnection() {
        try {
            dbConn = DriverManager.getConnection("jdbc:postgresql://51.158.111.171:5432/jee", "profjee", "Passw0rdProfJEE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}