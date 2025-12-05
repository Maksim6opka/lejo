package net.maksim6opka.lejo.events;

import net.maksim6opka.lejo.LejoHandler;
import de.myzelyam.api.vanish.PlayerHideEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class levanish implements Listener {
    @EventHandler
    public void onLeaveVanish(PlayerHideEvent event) {
            Player p = event.getPlayer();

            String rawMessage = LejoHandler.getMessage(p, "leave");
            Component parsed = MiniMessage.miniMessage().deserialize(rawMessage);
            Bukkit.broadcast(parsed);
    }
}
