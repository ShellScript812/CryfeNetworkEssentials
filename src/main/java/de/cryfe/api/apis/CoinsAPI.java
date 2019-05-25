package de.cryfe.api.apis;

import de.cryfe.api.manager.CoinsManager;
import org.bukkit.entity.Player;

public class CoinsAPI {

    Player player;
    String uuid;

    public CoinsAPI(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId().toString();
    }

    public CoinsAPI(String uuid) {
        this.uuid = uuid;
    }

    public Integer getCoins() {
        return CoinsManager.getCoins(this.uuid);
    }

    public void addCoins(int coins) {
        CoinsManager.addCoins(this.uuid, coins);
    }

    public void removeCoins(int coins) {
        CoinsManager.removeCoins(this.uuid, coins);
    }

    public void setCoins(int coins) {
        CoinsManager.removeCoins(this.uuid, coins);
    }

    public String getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }
}
