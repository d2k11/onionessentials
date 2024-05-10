package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

public class OnBookSign implements Listener {
    @EventHandler
    public void onBookSign(PlayerEditBookEvent event) {
        if(event.isSigning()) {
            event.setNewBookMeta(event.getNewBookMeta().author(Log.format(Usernames.get(event.getPlayer().getName()))));
        }
    }
}
