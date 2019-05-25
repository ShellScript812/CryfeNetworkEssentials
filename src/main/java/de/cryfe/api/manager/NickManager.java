package de.cryfe.api.manager;

import de.cryfe.api.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NickManager {

    public static boolean ifPlayerExist(String uuid) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Nick WHERE UUID='" + uuid + "'");
            if(rs.next()) {
                return rs.getString("UUID") != null;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) {
        if(!ifPlayerExist(uuid)) {
            Main.getMySQLManager().update("INSERT INTO Nick(UUID, NICK) VALUES ('" + uuid + "', '0');");
        }
    }

    public static Integer getActive(String uuid) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Nick WHERE UUID='" + uuid + "'");
            if(rs.next()) {
                return rs.getInt("NICK");
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setActive(String uuid, int active) {
        if(ifPlayerExist(uuid)) {
            Main.getMySQLManager().update("UPDATE Nick SET NICK='" + active + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
        }
    }

}
