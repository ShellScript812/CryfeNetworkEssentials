package de.cryfe.api.listener;

import de.cryfe.api.apis.ClientAPI;
import de.cryfe.api.apis.NickAPI;
import de.cryfe.api.manager.ApiManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

public class LIS_JoinQuit implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        ClientAPI clientAPI = new ClientAPI(playerJoinEvent.getPlayer());
        NickAPI nickAPI = new NickAPI(playerJoinEvent.getPlayer());
        clientAPI.registerPlayerToCoinsDatabase();
        nickAPI.createPlayer();

        clientAPI.updatePlayer(clientAPI.getUuidToString(), clientAPI.getName(),  clientAPI.getIpadress(), ApiManager.getString(playerJoinEvent.getPlayer(), "FIRSTJOIN"), String.valueOf(System.currentTimeMillis()));
    }
}
