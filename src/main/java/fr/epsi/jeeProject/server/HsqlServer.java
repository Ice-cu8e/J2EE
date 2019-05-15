/*package fr.epsi.jeeProject.server;

import org.hsqldb.persist.HsqlProperties;
import java.sql.Connection;
import java.sql.DriverManager;

public class HsqlServer {
    private final String dbLocation = "../../../web/db/blog"; // change it to your db location
    org.hsqldb.server.Server blogServer;
    Connection dbConn = null;

    public void startDBServer() {
        HsqlProperties props = new HsqlProperties();
        props.setProperty("server.database.0", "file:" + dbLocation);
        props.setProperty("server.dbname.0", "blog");
        blogServer = new org.hsqldb.Server();
        try {
            blogServer.setProperties(props);
        } catch (Exception e) {
            return;
        }
        blogServer.start();
    }

    public void stopDBServer() {
        blogServer.shutdown();
    }

    public Connection getDBConn() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            dbConn = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://localhost/blog", "SA", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}*/