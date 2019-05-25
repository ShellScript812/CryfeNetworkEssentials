package de.cryfe.api;

import de.cryfe.api.apis.DataAPI;
import de.cryfe.api.commands.CMD_Coins;
import de.cryfe.api.commands.CMD_Info;
import de.cryfe.api.listener.LIS_JoinQuit;
import de.cryfe.api.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private static MySQLManager mySQLManager;

    @Override
    public void onEnable() {
        instance = this;
        loadStrings();
        loadListener();
        loadCommands();
        loadMySQLManager();
        Bukkit.getConsoleSender().sendMessage(" > API wurde geladen");
    }

    @Override
    public void onDisable() {

    }

    private void loadCommands() {
        getCommand("coins").setExecutor(new CMD_Coins());
        getCommand("info").setExecutor(new CMD_Info());
    }

    private void loadListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new LIS_JoinQuit(), this);
    }

    private void loadStrings() {
        DataAPI dataAPI = new DataAPI();
        dataAPI.setPrefix("§e•§6● §lC§eryfeAPI §8▎ §7");
    }

    private void loadMySQLManager() {
        mySQLManager = new MySQLManager("localhost", "admin", "Z0HvcaOSVQDWHeTC", "APIManager");
        mySQLManager.connect();
        mySQLManager.createTable("Users", "UUID VARCHAR(100), NAME VARCHAR(16), IPADRESS VARCHAR(40), JOINS INTEGER, FIRSTJOIN TEXT, LASTJOIN TEXT");
        mySQLManager.createTable("Coins", "UUID VARCHAR(100), COINS INTEGER");
        mySQLManager.createTable("Nick", "UUID VARCHAR(100), NICK INTEGER");
    }

    public static MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    public static Main getInstance() {
        return instance;
    }
}
