package de.maksim6opka.lejo.events;

import de.maksim6opka.lejo.LejoHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.MetadataValue;

public class jo implements Listener {

    private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (!isVanished(event.getPlayer())) {

            Player p = event.getPlayer();

            String rawMessage = LejoHandler.getMessage(p, "join");
            Component parsed = MiniMessage.miniMessage().deserialize(rawMessage);
            Bukkit.broadcast(parsed);
        }
    }
}
