package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.Log;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class OnServerListPing implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.motd(Log.format("&8onion&7mc &8» &7anonymous minecraft"));
    }
}
