package net.maksim6opka.lejo.events;

import net.maksim6opka.lejo.LejoHandler;
import de.myzelyam.api.vanish.PlayerHideEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LeVanish implements Listener {
    @EventHandler
    public void onLeaveVanish(PlayerHideEvent event) {
            LejoHandler.sendMessage(event.getPlayer(), "leave");
    }
}
