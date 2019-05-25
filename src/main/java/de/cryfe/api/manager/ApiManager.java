package de.cryfe.api.manager;

import com.sun.security.ntlm.Client;
import de.cryfe.api.Main;
import de.cryfe.api.apis.ClientAPI;
import org.bukkit.entity.Player;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApiManager {

    //     UUID VARCHAR(100), NAME VARCHAR(16), IPADRESS VARCHAR(40), JOINS INTEGER, FIRSTJOIN TEXT, LASTJOIN TEXT

    public static boolean ifPlayerExist(Player clientAPI) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Users WHERE UUID='" + clientAPI.getUniqueId().toString() + "'");
            if(rs.next()) {
                return rs.getString("UUID") != null;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(Player clientAPI) {
        if(!ifPlayerExist(clientAPI)) {
            Main.getMySQLManager().update("INSERT INTO Users(UUID, NAME, IPADRESS, JOINS, FIRSTJOIN, LASTJOIN) VALUES ('" + clientAPI.getUniqueId().toString() + "', '" + clientAPI.getName() + "', '" + clientAPI.getAddress().getHostName() + "', '1', '" + System.currentTimeMillis() + "', '" + System.currentTimeMillis() + "');");
        }
    }

    public static Integer getInteger(Player player, String arg0) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Users WHERE UUID='" + player.getUniqueId().toString() + "'");
            if(rs.next()) {
                return rs.getInt(arg0);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getString(Player clientAPI, String arg0) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Users WHERE UUID='" + clientAPI.getUniqueId().toString() + "'");
            if(rs.next()) {
                return rs.getString(arg0);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static long getLong(Player clientAPI, String arg0) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Users WHERE UUID='" + clientAPI.getUniqueId().toString() + "'");
            if(rs.next()) {
                return rs.getLong(arg0);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void setString(Player clientAPI, String arg0, String arg1) {
        if(ifPlayerExist(clientAPI)) {
            Main.getMySQLManager().update("UPDATE Users SET " + arg0 + "='" + arg1 + "' WHERE UUID='" + clientAPI.getUniqueId().toString() + "'");
        } else {
            createPlayer(clientAPI);
        }
    }

    public static void setInteger(Player clientAPI, String arg0, int arg1) {
        if(ifPlayerExist(clientAPI)) {
            Main.getMySQLManager().update("UPDATE Users SET " + arg0 + "='" + arg1 + "' WHERE UUID='" + clientAPI.getUniqueId().toString() + "'");
        } else {
            createPlayer(clientAPI);
        }
    }
}
