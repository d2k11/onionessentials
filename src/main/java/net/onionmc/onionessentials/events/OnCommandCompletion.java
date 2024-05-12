package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.systems.Captcha;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.List;

public class OnCommandCompletion implements Listener {

    public List<String> allowedCommands = List.of("tp", "reset", "kill", "help", "color", "idle", "yell", "hide", "channel", "list");
    @EventHandler
    public void onChatTab(PlayerCommandSendEvent event) {
        event.getCommands().clear();
        event.getCommands().addAll(allowedCommands);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(Captcha.isJailed(event.getPlayer().getName())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Log.format("&cYou do not have permission to use this command."));
        }
        if(!allowedCommands.contains(event.getMessage().split(" ")[0].substring(1))) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Log.format("&cYou do not have permission to use this command."));
        }
    }
}
