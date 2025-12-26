package net.maksim6opka.lejo.events;

import net.maksim6opka.lejo.Lejo;
import net.maksim6opka.lejo.LejoHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Le implements Listener {


    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        if (!LejoHandler.isVanished(event.getPlayer())) {

            LejoHandler.sendMessage(event.getPlayer(), "leave");
            if (Lejo.getPlugin(Lejo.class).getConfig().getBoolean("messages.disable-default-server-messages")) {
                event.quitMessage(null);
            }
        }
    }
}

