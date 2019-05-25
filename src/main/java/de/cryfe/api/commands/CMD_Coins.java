package de.cryfe.api.commands;

import de.cryfe.api.apis.ClientAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Coins implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            ClientAPI clientAPI = new ClientAPI((Player)sender);
            if(args.length == 0) {
                clientAPI.sendMessage("Du besitzt §6§l" + clientAPI.getCoins() + " §7Coins.");
            } else if(args.length == 1) {
                String name = args[0];
                ClientAPI targetAPI = new ClientAPI(Bukkit.getPlayer(name));
                if (targetAPI.isOnline()) {
                    clientAPI.sendMessage("Der Spieler §6§l" + targetAPI.getName() + " §7hat §6§l" + targetAPI.getCoins() + " §7Coins.");
                } else {
                    clientAPI.sendTitle("§4§lX §r§4Fehler §4§lX", "§eSpieler nicht online.");
                }

            } else if(args.length == 3) {
                if(clientAPI.hasPermissions("cryfeapi.coins")) {
                    if (args[0].equalsIgnoreCase("set")) {
                        String name = args[1];
                        if (Bukkit.getPlayer(name) != null) {
                            ClientAPI targetAPI = new ClientAPI(Bukkit.getPlayer(name));
                            try {
                                int valueCoins = Integer.parseInt(args[2]);
                                targetAPI.setCoins(valueCoins);
                                if(targetAPI.getCoins() < 0) {
                                    targetAPI.setCoins(0);
                                }
                                clientAPI.sendMessage("Der Spieler §6§l" + targetAPI.getName() + " §7hat nun §6§l" + valueCoins + " §7Coins.");
                            } catch (NumberFormatException ex) {

                            }
                        } else {
                            clientAPI.sendTitle("§4§lX §r§4Fehler §4§lX", "§eSpieler nicht online.");
                        }
                    } else if(args[0].equalsIgnoreCase("add")) {
                        String name = args[1];

                        if (Bukkit.getPlayer(name) != null) {
                            ClientAPI targetAPI = new ClientAPI(Bukkit.getPlayer(name));
                            try {
                                int valueCoins = Integer.parseInt(args[2]);
                                targetAPI.addCoins(valueCoins);
                                clientAPI.sendMessage("Der Spieler §6§l" + targetAPI.getName() + " §7hat nun §6§l" + valueCoins + " §7bekommen.");
                            } catch (NumberFormatException ex) {

                            }
                        } else {
                            clientAPI.sendTitle("§4§lX §r§4Fehler §4§lX", "§eSpieler nicht online.");
                        }
                    } else if(args[0].equalsIgnoreCase("remove")) {
                        String name = args[1];
                        if (Bukkit.getPlayer(name) != null) {
                            ClientAPI targetAPI = new ClientAPI(Bukkit.getPlayer(name));
                            try {
                                int valueCoins = Integer.parseInt(args[2]);
                                targetAPI.removeCoins(valueCoins);
                                if(targetAPI.getCoins() < 0) {
                                    targetAPI.setCoins(0);
                                }
                                clientAPI.sendMessage("Dem Spieler §6§l" + targetAPI.getName() + " §7wurde §6§l" + valueCoins + " §7Coins entfernt.");
                            }catch(NumberFormatException ex) {

                            }
                        } else {
                            clientAPI.sendTitle("§4§lX §r§4Fehler §4§lX", "§eSpieler nicht online.");
                        }
                    } else {
                        clientAPI.sendUsage("/coins [set,add,remove] [SPIELER] COINS");
                    }
                } else {
                    clientAPI.sendTitle("§4§lX §r§4Fehler §4§lX", "§cKeine Berechtigung.");
                }
            } else {
                clientAPI.sendUsage("/coins");
            }
        } else {
            sender.sendMessage("Nö");
        }
        return false;
    }
}
