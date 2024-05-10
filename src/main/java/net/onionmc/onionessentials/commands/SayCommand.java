package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.models.UserSettings;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof ConsoleCommandSender) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(Log.format("&dServer &8» &f" + String.join(" ", strings)));
            }
            Bukkit.getConsoleSender().sendMessage(Log.format("&dServer &8» &f" + String.join(" ", strings)));
        }
        return true;
    }
}
