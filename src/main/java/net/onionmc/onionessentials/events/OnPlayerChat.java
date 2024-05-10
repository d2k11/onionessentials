package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.models.UserSettings;
import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class OnPlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        event.setCancelled(true);

        // If the player is jailed, check if the message is the solution to the captcha
        if(Captcha.isJailed(event.getPlayer().getName())) {
            if(event.getMessage().equals(Captcha.getJailSolution(event.getPlayer().getName()))) {
                event.getPlayer().sendMessage(Log.format("&aLogin successful!"));
                Captcha.unjail(event.getPlayer().getName());
            } else {
                event.getPlayer().kick(Log.format("&cIncorrect captcha solution. Please try again."));
            }
            return;
        }

        UserSettings settings = Usernames.getSettings(event.getPlayer().getName());

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(event.getMessage().contains("@" + Usernames.get(player.getName()).split(" ")[1])) {
                player.sendMessage(Log.format(settings.color + Usernames.get(event.getPlayer().getName()) + " &e» &f" + event.getMessage()));
            }
            else {
                player.sendMessage(Log.format(settings.color + Usernames.get(event.getPlayer().getName()) + " &8» &f" + event.getMessage()));
            }
        }

        Bukkit.getConsoleSender().sendMessage(Usernames.get(event.getPlayer().getName()) + " » " + event.getMessage());
    }
}
