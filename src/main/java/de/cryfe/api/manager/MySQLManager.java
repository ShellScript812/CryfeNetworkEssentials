package de.cryfe.api.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("WeakerAccess")
public class MySQLManager {

    public static Connection con;

    private String host;
    private String name;
    private String password;
    private String database;

    public MySQLManager(String host, String user, String pw, String db) {
        this.host = host;
        this.name = user;
        this.password = pw;
        this.database = db;
    }

    public void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", name, password);
                System.out.println("[MySQL] Verbindung zur MySQL erfolgreich!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[MySQL] §4Fehler: §c" + e.getMessage());

            }

        }
    }

    public void close() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[MySQL] Verbindung zur MySQL beendet!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[MySQL] §4Fehler: §c" + e.getMessage());

            }
        }
    }

    public boolean isConnected() {
        return con != null;

    }

    public void createTable(String name, String table) {
        try {
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS " + name + "(" + table + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String qry) {
        if(isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getResult(String qry) {
        if(isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
