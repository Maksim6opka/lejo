package net.maksim6opka.lejo;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LejoHandler {

    private static final Random random = new Random();

    public static String getMessage(Player p, String type) {
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

        // Форматування дати та часу
        String timeFormat = config.getString("messages.placeholders.time-format", "HH:mm:ss");
        String dateFormat = config.getString("messages.placeholders.date-format", "dd.MM.yyyy");
        String locationFormat = config.getString("messages.placeholders.location-format", "x:{x} y:{y} z:{z}");

        String servertime = LocalTime.now().format(DateTimeFormatter.ofPattern(timeFormat));
        String serverdate = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));

        // Координати
        Location loc = p.getLocation();
        String location = locationFormat
                .replace("{x}", String.valueOf(loc.getBlockX()))
                .replace("{y}", String.valueOf(loc.getBlockY()))
                .replace("{z}", String.valueOf(loc.getBlockZ()));

        String message = null;

        // Спочатку перевірка за нікнеймом
        ConfigurationSection nicknames = config.getConfigurationSection("messages.nickname");
        if (nicknames != null && nicknames.contains(nickname)) {
            message = getString(nicknames, nickname, type);
        }

        // Якщо не знайшло — шукаємо по permission
        if (message == null) {
            ConfigurationSection perms = config.getConfigurationSection("messages.permission");
            if (perms != null) {
                Set<String> keys = perms.getKeys(false);
                for (String perm : keys) {
                    if (p.hasPermission(perm)) {
                        message = getString(perms, perm, type);
                        break;
                    }
                }
            }
        }

        // Якщо все ще нічого — дефолтне повідомлення
        if (message == null) {
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

        // Префікс
        String prefix = config.getString("prefix." + type, type.equals("join") ? "[+]" : "[-]");
        boolean suffix = config.getBoolean("prefix.suffix-mode", false);

        if (suffix) {
            message = message + " " + prefix;
        } else {
            message = prefix + " " + message;
        }

        // Заміна плейсхолдерів
        return message
                .replace("{p}", nickname)
                .replace("{t}", servertime)
                .replace("{d}", serverdate)
                .replace("{l}", location)
                .replace("{w}", world);
    }

    private static String getString(ConfigurationSection section, String key, String type) {
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
}
