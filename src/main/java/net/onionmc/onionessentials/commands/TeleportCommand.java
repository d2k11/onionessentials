package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player)commandSender;
        if (strings.length != 3) {
            player.sendMessage(Log.format("&cUsage: /tp <x> <y> <z>"));
            return true;
        }
        try {
            double x = Double.parseDouble(strings[0]);
            double y = Double.parseDouble(strings[1]);
            double z = Double.parseDouble(strings[2]);
            if(x > 128000 || x < -128000 || y > 128000 || y < -128000 || z > 128000 || z < -128000) {
                player.sendMessage(Log.format("&cCoordinates out of bounds"));
                return true;
            }
            player.teleport(player.getWorld().getBlockAt((int)x, (int)y, (int)z).getLocation());
        }
        catch (NumberFormatException e) {
            player.sendMessage(Log.format("&cInvalid coordinates"));
        }
        return true;
    }
}
