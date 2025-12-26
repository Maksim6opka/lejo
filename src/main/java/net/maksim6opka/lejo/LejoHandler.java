package net.maksim6opka.lejo;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import me.clip.placeholderapi.PlaceholderAPI;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LejoHandler {

    private static final Random random = new Random();

    public static void sendMessage(Player p, String type) {
        Lejo plugin = Lejo.getPlugin(Lejo.class);
        FileConfiguration config = plugin.getConfig();

        String nickname = p.getName();
        String worldname = p.getWorld().getName();
        String world = switch (worldname) {
            case "world" -> config.getString("messages.placeholders.worlds.overworld", "overworld");
            case "world_nether" -> config.getString("messages.placeholders.worlds.nether", "nether");
            case "world_the_end" -> config.getString("messages.placeholders.worlds.end", "end");
            default -> config.getString("messages.placeholders.worlds.custom", "custom");
        };

        //
        String timeFormat = config.getString("messages.placeholders.time-format", "HH:mm:ss");
        String dateFormat = config.getString("messages.placeholders.date-format", "dd.MM.yyyy");
        String locationFormat = config.getString("messages.placeholders.location-format", "x:{x} y:{y} z:{z}");

        String servertime = LocalTime.now().format(DateTimeFormatter.ofPattern(timeFormat));
        String serverdate = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));

        //
        Location loc = p.getLocation();
        String location = locationFormat
                .replace("{x}", String.valueOf(loc.getBlockX()))
                .replace("{y}", String.valueOf(loc.getBlockY()))
                .replace("{z}", String.valueOf(loc.getBlockZ()));

        String message = null;

        //

        if (Lejo.getPlugin(Lejo.class).getConfig().getBoolean("messages.nickname.enabled")) {
            ConfigurationSection nicknames = config.getConfigurationSection("messages.nickname");
            if (nicknames != null && nicknames.contains(nickname)) {
                message = getRandomMsg(nicknames, nickname, type);
            }
        }

        //
        if (message == null) {
            if (Lejo.getPlugin(Lejo.class).getConfig().getBoolean("messages.parent.enabled")) {
                ConfigurationSection perms = config.getConfigurationSection("messages.parent");
                if (perms != null) {
                    Set<String> keys = perms.getKeys(false);
                    for (String perm : keys) {
                        if (p.hasPermission(perm)) {
                            message = getRandomMsg(perms, perm, type);
                            break;
                        }
                    }
                }
            }
        }

        //
        if (message == null) {
            if (!Lejo.getPlugin(Lejo.class).getConfig().getBoolean("messages.default.enabled")) {
                return;
            }
                Object value = config.get("messages.default." + type);
                if (value instanceof List) {
                    List<String> options = config.getStringList("messages.default." + type);
                    if (!options.isEmpty()) {
                        message = options.get(random.nextInt(options.size()));
                    }
                } else if (value instanceof String) {
                    message = (String) value;
                }

        }

        //

        String prefix = config.getString("prefix." + type, type.equals("join") ? "[+]" : "[-]");

        if (config.getBoolean("prefix.suffix-mode", false)) {
            message = message + " " + prefix;
        } else {
            message = prefix + " " + message;
        }

        //
        String formmsg = message
                .replace("{p}", nickname)
                .replace("{t}", servertime)
                .replace("{d}", serverdate)
                .replace("{l}", location)
                .replace("{w}", world);

        String formatted = setPlaceholders(p, formmsg);
        Component parsed = MiniMessage.miniMessage().deserialize(formatted);
        Bukkit.broadcast(parsed);
    }

    private static String getRandomMsg(ConfigurationSection section, String key, String type) {
        Object value = section.get(key + "." + type);
        if (value instanceof List) {
            List<String> options = section.getStringList(key + "." + type);
            if (!options.isEmpty()) {
                return options.get(random.nextInt(options.size()));
            }
        } else if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    public static boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

    public static String setPlaceholders(Player player, String text) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }
        return text;
    }

}
