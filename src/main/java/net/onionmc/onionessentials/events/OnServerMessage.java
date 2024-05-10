package net.onionmc.onionessentials.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class OnServerMessage implements Listener {
    @EventHandler
    public void onDeathMessage(PlayerDeathEvent event) {
        event.deathMessage(null);
    }

    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        event.message(null);
    }
}
