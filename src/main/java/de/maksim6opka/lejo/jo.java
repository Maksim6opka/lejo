package de.maksim6opka.lejo;



import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.MetadataValue;


import java.util.List;
import java.util.Random;
import java.util.Set;

public class jo implements Listener {

    private final MiniMessage mm = MiniMessage.miniMessage();
    private final Random random = new Random();

    private boolean isVanished(Player player) {
        if (!player.hasMetadata("vanished")) return false;

        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (isVanished(p)) return;

        Lejo plugin = Lejo.getPlugin(Lejo.class);

        String nickname = p.getName();
        String message = null;

        // 1. Перевірка на nickname (може бути список або рядок)
        ConfigurationSection nicknames = plugin.getConfig().getConfigurationSection("messages.nickname");
        if (nicknames != null && nicknames.contains(nickname)) {
            message = getString(message, nicknames, nickname);
        }

        // 2. Перевірка на права
        if (message == null) {
            ConfigurationSection perms = plugin.getConfig().getConfigurationSection("messages.permission");
            if (perms != null) {
                Set<String> keys = perms.getKeys(false);
                for (String perm : keys) {
                    if (p.hasPermission(perm)) {
                        message = getString(message, perms, perm);
                        break;
                    }
                }
            }
        }

        // 3. Якщо нічого не знайдено — default
        if (message == null) {
            Object value = plugin.getConfig().get("messages.default.join");
            if (value instanceof List) {
                List<String> options = plugin.getConfig().getStringList("messages.default.join");
                if (!options.isEmpty()) {
                    message = options.get(random.nextInt(options.size()));
                }
            } else if (value instanceof String) {
                message = (String) value;
            }
        }

        // 4. Префікс
        String prefix = plugin.getConfig().getString("prefix.join", "[+]");
        boolean suffix = plugin.getConfig().getBoolean("prefix.suffix-mode", false);

        if (suffix) {
            message = message + " " + prefix;
        } else {
            message = prefix + " " + message;
        }

        // 5. Підстановка {p} і показ
        message = message.replace("{p}", nickname);
        Component parsed = mm.deserialize(message);
        Bukkit.broadcast(parsed);
    }

    private String getString(String message, ConfigurationSection perms, String perm) {
        Object value = perms.get(perm + ".join");
        if (value instanceof List) {
            List<String> options = perms.getStringList(perm + ".join");
            if (!options.isEmpty()) {
                message = options.get(random.nextInt(options.size()));
            }
        } else if (value instanceof String) {
            message = (String) value;
        }
        return message;
    }
}
