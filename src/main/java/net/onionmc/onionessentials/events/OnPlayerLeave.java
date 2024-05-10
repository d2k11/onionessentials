package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.http.WebSocket;

public class OnPlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.quitMessage(null);
        if(!Captcha.isJailed(event.getPlayer().getName())) {
            Bukkit.getServer().broadcast(Log.format(Usernames.getSettings(event.getPlayer().getName()).color + Usernames.get(event.getPlayer().getName()) + " &4»"));
        }
    }
}
