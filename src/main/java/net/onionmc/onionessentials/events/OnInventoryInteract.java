package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class OnInventoryInteract implements Listener
{
    @EventHandler
    public void onInventoryInteract(InventoryCreativeEvent event)
    {
        if(Captcha.isJailed(event.getWhoClicked().getName()) || Usernames.getSettings(event.getWhoClicked().getName()).idle) {
            event.setCancelled(true);
        }
    }
}
