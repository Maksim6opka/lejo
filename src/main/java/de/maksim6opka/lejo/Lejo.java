package de.maksim6opka.lejo;

import org.bukkit.plugin.java.JavaPlugin;

public final class Lejo extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new jo(), this);
        getServer().getPluginManager().registerEvents(new le(), this);

        // Реєстрація команди
        this.getCommand("lejo").setExecutor(new lejoreload(this));

        saveDefaultConfig();
        getLogger().info(getConfig().getString("messages.system.enable"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(getConfig().getString("message.system.disable"));
    }
}
