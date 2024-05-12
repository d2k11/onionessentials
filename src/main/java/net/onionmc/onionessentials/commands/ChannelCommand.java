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

import java.util.logging.LogManager;

public class ChannelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        UserSettings settings = Usernames.getSettings(player.getName());
        if(strings.length == 0) {
            player.sendMessage(Log.format("&7&m-----------------------------"));
            player.sendMessage(Log.format("&7You are currently in Channel &f"+settings.channel + (settings.channel == 0 ? " &7(global)" : "")));
            player.sendMessage(Log.format("&7&m-----------------------------"));
            player.sendMessage(Log.format("&81.&7 Use &f/channel <number> &7to switch channels. "));
            player.sendMessage(Log.format("&82.&7 Everyone can always see 0 (global)."));
            player.sendMessage(Log.format("&83.&7 You can only see messages from your channel."));
            player.sendMessage(Log.format("&7&m-----------------------------"));
            return true;
        }
        if(strings.length == 1) {
            try {
                int channel = Integer.parseInt(strings[0]);
                if(channel < 0 || channel > 100) {
                    player.sendMessage(Log.format("&cInvalid channel (must be between 0 and 100)"));
                    return true;
                }
                if(settings.channel == channel) {
                    player.sendMessage(Log.format("&cYou are already in Channel #"+channel));
                }
                Bukkit.getServer().broadcast(Log.format("&7<0> &7&oServer Message &r&8» " + settings.color + Usernames.get(player.getName()) + "&7 switched channel to &f" + channel));
                settings.channel = channel;
                Usernames.putSettings(player.getName(), settings);
            } catch (NumberFormatException e) {
                player.sendMessage(Log.format("&cInvalid channel (must be a number)"));
            }
        }
        return true;
    }
}
