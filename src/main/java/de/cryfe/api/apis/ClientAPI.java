package de.cryfe.api.apis;

import de.cryfe.api.manager.ApiManager;
import de.cryfe.api.manager.CoinsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClientAPI {

    Player player;
    String name;
    UUID uuid;
    String uuidToString;
    DataAPI dataAPI;
    String ipadress;

    public ClientAPI(Player player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.uuidToString = player.getUniqueId().toString();
        this.dataAPI = new DataAPI();
        this.ipadress = player.getAddress().getHostString();
    }

    public void sendMessage(String arg0) { this.player.sendMessage(dataAPI.getPrefix() + arg0); }

    public void sendTitle(String arg0, String arg1) { this.player.sendTitle(arg0, arg1); }

    public void sendUsage(String arg0) { sendMessage("Verwende: §e" + arg0); }

    public void teleportPlayer(Location arg0) { this.player.teleport(arg0); }

    public void teleportToPlayer(Player arg0) { teleportPlayer(arg0.getLocation()); }

    //   <-- COINS -->

    public void registerPlayerToCoinsDatabase() { CoinsManager.createPlayer(this.uuidToString); }

    public void addCoins(int arg0) { CoinsManager.addCoins(this.uuidToString, arg0); }

    public void removeCoins(int arg0) { CoinsManager.removeCoins(this.uuidToString, arg0); }

    public void setCoins(int arg0) { CoinsManager.setCoins(this.uuidToString, arg0); }

    public Integer getCoins() { return CoinsManager.getCoins(this.uuidToString); }

    // ________________________________________________________________________________________


    //     UUID VARCHAR(100), NAME VARCHAR(16), IPADRESS VARCHAR(40), JOINS INTEGER, FIRSTJOIN TEXT, LASTJOIN TEXT

    public void updatePlayer(String uuid, String name, String ipadress, String firstjoin, String lastjoin) {
        if(!(ApiManager.ifPlayerExist(this.player))) {
            ApiManager.createPlayer(this.player);
        } else {
            ApiManager.setString(this.player, "UUID", uuid);
            ApiManager.setString(this.player, "NAME", name);
            Player player = Bukkit.getPlayer(name);
            int joins = ApiManager.getInteger(player, "JOINS");
            joins++;
            ApiManager.setInteger(this.player, "JOINS", joins);
            ApiManager.setString(this.player, "IPADRESS", ipadress);
            ApiManager.setString(this.player, "FIRSTJOIN", firstjoin);
            ApiManager.setString(this.player, "LASTJOIN", lastjoin);
        }
    }

    // ________________________________________________________________________________________

    public boolean hasPermissions(String arg0) { return this.player.hasPermission(arg0); }

    public boolean isOnline() { return this.player != null; }

    public Location getLocation() { return this.player.getLocation(); }

    public String getIpadress() {
        return ipadress;
    }

    public UUID getUuid() { return uuid; }

    public String getUuidToString() { return uuidToString; }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }
}
