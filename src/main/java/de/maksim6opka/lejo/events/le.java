package de.maksim6opka.lejo.events;

import de.maksim6opka.lejo.LejoHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class le implements Listener {


    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();

        String rawMessage = LejoHandler.getMessage(p, "leave");
        Component parsed = MiniMessage.miniMessage().deserialize(rawMessage);
        Bukkit.broadcast(parsed);
    }
}

