package de.cryfe.api.manager;

import de.cryfe.api.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsManager {

    public static boolean ifPlayerExist(String uuid) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Coins WHERE UUID='" + uuid + "'");
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
            Main.getMySQLManager().update("INSERT INTO Coins(UUID, COINS) VALUES ('" + uuid + "', '0');");
        }
    }

    public static Integer getCoins(String uuid) {
        try {
            ResultSet rs = Main.getMySQLManager().getResult("SELECT * FROM Coins WHERE UUID='" + uuid + "'");
            if(rs.next()) {
                return rs.getInt("COINS");
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setCoins(String uuid, int coins) {
        if(ifPlayerExist(uuid)) {
            Main.getMySQLManager().update("UPDATE Coins SET COINS='" + coins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
        }
    }

    public static void addCoins(String uuid, int coins) {
        if(ifPlayerExist(uuid)) {
            int newCoins = getCoins(uuid) + coins;
            Main.getMySQLManager().update("UPDATE Coins SET COINS='" + newCoins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
        }
    }

    public static void removeCoins(String uuid, int coins) {
        if(ifPlayerExist(uuid)) {
            int newCoins = getCoins(uuid) - coins;
            Main.getMySQLManager().update("UPDATE Coins SET COINS='" + newCoins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
        }
    }
}
