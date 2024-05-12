package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.models.UserSettings;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        UserSettings settings = Usernames.getSettings(player.getName());

        if(strings.length == 0) {
            if(!settings.visible) {
                commandSender.sendMessage(Log.format("&7&m---&r&e WARNING &7&m---"));
                commandSender.sendMessage(Log.format("&7Revealing yourself will make you (and your username) visible to &lall&r&7 players."));
                commandSender.sendMessage(Log.format("&7Use &e/hide confirm &7if you'd like to proceed anyway."));
                commandSender.sendMessage(Log.format("&7You can use &e/hide&7 without confirm to re-hide yourself."));
                return true;
            }
            else {
                commandSender.sendMessage(Log.format("&7You are now invisible."));
                settings.visible = false;
                Usernames.putSettings(player.getName(), settings);

                player.setInvisible(true);
                for(Player online : Bukkit.getOnlinePlayers()) {
                    online.hidePlayer(player);
                }
                return true;
            }
        }
        if(strings.length == 1 && strings[0].equals("confirm")) {
            if(settings.visible) {
                commandSender.sendMessage(Log.format("&7You are already visible."));
                return true;
            }
            commandSender.sendMessage(Log.format("&7You are now visible to &call&7 players."));
            settings.visible = true;
            Usernames.putSettings(player.getName(), settings);

            player.setInvisible(false);
            for(Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(player);
            }
            return true;
        }
        return true;
    }
}
