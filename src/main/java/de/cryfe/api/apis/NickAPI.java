package de.cryfe.api.apis;

import de.cryfe.api.manager.NickManager;
import org.bukkit.entity.Player;

public class NickAPI {

    Player player;
    String uuid;

    public NickAPI(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId().toString();
    }

    public void createPlayer(){
        if(this.player.hasPermission("cryfeapi.nick")) {
            NickManager.createPlayer(this.uuid);
        }
    }

    public void setActive(int active) {
        NickManager.setActive(this.uuid, active);
    }

    public boolean isActive() {
        return NickManager.getActive(this.uuid) == 1;
    }

    public Player getPlayer() {
        return player;
    }

    public String getUuid() {
        return uuid;
    }
}
