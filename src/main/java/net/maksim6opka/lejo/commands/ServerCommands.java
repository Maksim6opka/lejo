package net.maksim6opka.lejo.commands;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.maksim6opka.lejo.Lejo;

@SuppressWarnings("UnstableApiUsage")

public class ServerCommands {
    private final Lejo plugin;

    public ServerCommands(Lejo plugin) {
        this.plugin = plugin;
    }

    public void registerPluginCommands(){
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final var registrar = event.registrar();

            registrar.register(
                    Commands.literal("reload")
                            .requires( req -> req.getSender().hasPermission("lejo.reload"))
                            .executes( ctx -> {
                                var sender = ctx.getSource().getSender();
                                plugin.reloadConfig();
                                String msg = plugin.getConfig().getString("messages.system.reload", "Lejo plugin reloaded");
                                sender.sendMessage("§a" + msg);

                                return 1;
                            } )
                            .build()
            );

        });
    }


}
