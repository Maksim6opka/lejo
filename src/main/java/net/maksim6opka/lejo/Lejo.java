package net.maksim6opka.lejo;

import net.maksim6opka.lejo.commands.PluginCommands;
import net.maksim6opka.lejo.events.Jo;
import net.maksim6opka.lejo.events.JoVanish;
import net.maksim6opka.lejo.events.Le;
import net.maksim6opka.lejo.events.LeVanish;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lejo extends JavaPlugin {

    @Override
    public void onEnable() {

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Jo(), this);
        pm.registerEvents(new Le(), this);
        PluginCommands reloadCommand = new PluginCommands(this);
        reloadCommand.registerPluginCommands();


        if (pm.isPluginEnabled("SuperVanish") || pm.isPluginEnabled("PremiumVanish")) {
            getLogger().info("Vanish is detected");
            pm.registerEvents(new JoVanish(), this);
            pm.registerEvents(new LeVanish(), this);
        }
        else getLogger().warning("Vanish is not detected");

        if (pm.getPlugin("PlaceholderAPI") != null) {
            getLogger().info("PlaceholderAPI is detected");
        } else {
            getLogger().warning("PlaceholderAPI is not detected");
        }


        saveDefaultConfig();
        getLogger().info(getConfig().getString("messages.system.enable"));
    }

    @Override
    public void onDisable() {
        getLogger().info(getConfig().getString("message.system.disable"));
    }

}