package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.models.UserSettings;
import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IdleCommand implements CommandExecutor {
    private HashMap<String, Location> previousLocations = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        UserSettings settings = Usernames.getSettings(player.getName());
        if(settings.idle) {
            settings.idle = false;
            player.teleport(previousLocations.get(player.getName()));
            player.sendMessage(Log.format("&7You are no longer idling."));
        } else {
            settings.idle = true;
            previousLocations.put(player.getName(), player.getLocation());
            player.teleport(new Location(Bukkit.getWorld("world_the_end"), 0, Captcha.rand(1000, 10000), 0));
            player.sendMessage(Log.format("&7You are now idling."));
        }
        return true;
    }
}
