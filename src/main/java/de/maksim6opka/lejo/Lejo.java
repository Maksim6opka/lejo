package de.maksim6opka.lejo;

import de.maksim6opka.lejo.commands.lejoreload;
import de.maksim6opka.lejo.events.jo;
import de.maksim6opka.lejo.events.jovanish;
import de.maksim6opka.lejo.events.le;
import de.maksim6opka.lejo.events.levanish;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lejo extends JavaPlugin {

    @Override
    public void onEnable() {

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new jo(), this);
        pm.registerEvents(new le(), this);

        if (pm.isPluginEnabled("SuperVanish") || pm.isPluginEnabled("PremiumVanish")) {
            pm.registerEvents(new jovanish(), this);
            pm.registerEvents(new levanish(), this);
        }

        this.getCommand("lejo").setExecutor(new lejoreload(this));

        saveDefaultConfig();
        getLogger().info(getConfig().getString("messages.system.enable"));
    }

    @Override
    public void onDisable() {
        getLogger().info(getConfig().getString("message.system.disable"));
    }
}