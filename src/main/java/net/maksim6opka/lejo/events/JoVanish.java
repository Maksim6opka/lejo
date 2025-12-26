package net.maksim6opka.lejo.events;

import net.maksim6opka.lejo.LejoHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import de.myzelyam.api.vanish.PlayerShowEvent;

public class JoVanish implements Listener {
    @EventHandler
    public void onJoinVanish(PlayerShowEvent event) {
        Player p = event.getPlayer();

        String rawMessage = LejoHandler.getMessage(p, "join");
        Component parsed = MiniMessage.miniMessage().deserialize(rawMessage);
        Bukkit.broadcast(parsed);
    }
}
