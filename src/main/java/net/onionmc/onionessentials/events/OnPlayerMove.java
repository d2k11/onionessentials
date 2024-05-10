package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(Captcha.isJailed(event.getPlayer().getName()) || Usernames.getSettings(event.getPlayer().getName()).idle) {
            event.setCancelled(true);
        }
    }
}
