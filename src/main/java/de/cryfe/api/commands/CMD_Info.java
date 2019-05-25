package de.cryfe.api.commands;

import de.cryfe.api.apis.ClientAPI;
import de.cryfe.api.manager.ApiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CMD_Info implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            ClientAPI clientAPI = new ClientAPI((Player)commandSender);
            if(args.length == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
                clientAPI.sendMessage("Informationen über dich.");
                clientAPI.sendMessage("UUID: §6§l" + ApiManager.getString(clientAPI.getPlayer(), "UUID"));
                clientAPI.sendMessage("Name: §6§l" + ApiManager.getString(clientAPI.getPlayer(), "NAME"));
                clientAPI.sendMessage("IP: §6§l" + ApiManager.getString(clientAPI.getPlayer(), "IPADRESS"));
                clientAPI.sendMessage("Joins: §6§l" + ApiManager.getString(clientAPI.getPlayer(), "Joins"));
                clientAPI.sendMessage("Erster Join: §6§l" + sdf.format(ApiManager.getLong(clientAPI.getPlayer(), "FIRSTJOIN")));
                clientAPI.sendMessage("Letzter Join: §6§l" + sdf.format(ApiManager.getLong(clientAPI.getPlayer(), "LASTJOIN")));
                clientAPI.sendMessage("Coins: §6§l" + clientAPI.getCoins());
            } else {
                clientAPI.sendUsage("/info");
            }
        } else {
            commandSender.sendMessage("Nö");
        }
        return false;
    }
}
