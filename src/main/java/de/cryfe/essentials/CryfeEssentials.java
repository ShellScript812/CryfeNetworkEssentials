package de.cryfe.essentials;

import org.bukkit.plugin.java.JavaPlugin;

public class CryfeEssentials extends JavaPlugin {

    private static CryfeEssentials instance;

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public static CryfeEssentials getInstance() {
        return instance;
    }
}
