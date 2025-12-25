package net.maksim6opka.lejo;

import net.maksim6opka.lejo.commands.ServerCommands;
import net.maksim6opka.lejo.events.jo;
import net.maksim6opka.lejo.events.jovanish;
import net.maksim6opka.lejo.events.le;
import net.maksim6opka.lejo.events.levanish;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lejo extends JavaPlugin {

    @Override
    public void onEnable() {
//
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new jo(), this);
        pm.registerEvents(new le(), this);
        ServerCommands serverCommands = new ServerCommands(this);
        serverCommands.registerPluginCommands();


        if (pm.isPluginEnabled("SuperVanish") || pm.isPluginEnabled("PremiumVanish")) {
            getLogger().info("Vanish is detected");
            pm.registerEvents(new jovanish(), this);
            pm.registerEvents(new levanish(), this);
        }
        else getLogger().info("Vanish is not detected");


        saveDefaultConfig();
        getLogger().info(getConfig().getString("messages.system.enable"));
    }

    @Override
    public void onDisable() {
        getLogger().info(getConfig().getString("message.system.disable"));
    }
}