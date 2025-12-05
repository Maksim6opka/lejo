package net.maksim6opka.lejo.commands;

import net.maksim6opka.lejo.Lejo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class lejoreload implements CommandExecutor {

    private final Lejo plugin;

    public lejoreload(Lejo plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("lejo.reload")) {
                sender.sendMessage("§cУ тебе немає прав на виконання цієї команди.");
                return true;
            }

            plugin.reloadConfig();
            String msg = plugin.getConfig().getString("messages.system.reload", "Lejo plugin reloaded");
            sender.sendMessage("§a" + msg);
            return true;
        }

        sender.sendMessage("§eВикористання: /lejo reload");
        return true;
    }
}
