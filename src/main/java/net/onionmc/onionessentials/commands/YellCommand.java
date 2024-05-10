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

public class YellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        String id = Usernames.get(player.getName());
        UserSettings settings = Usernames.getSettings(player.getName());
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(Log.format(settings.color + id + " &4» &f" + String.join(" ", strings)));
        }
        Bukkit.getConsoleSender().sendMessage(Log.format(settings.color + id + " &4» &f" + String.join(" ", strings)));
        return true;
    }
}
