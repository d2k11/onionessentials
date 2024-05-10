package net.onionmc.onionessentials.commands;

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
            player.sendMessage("Usage: /tp <x> <y> <z>");
            return true;
        }
        try {
            double x = Double.parseDouble(strings[0]);
            double y = Double.parseDouble(strings[1]);
            double z = Double.parseDouble(strings[2]);
            player.teleport(player.getWorld().getBlockAt((int)x, (int)y, (int)z).getLocation());
        }
        catch (NumberFormatException e) {
            player.sendMessage("Invalid coordinates");
        }
        return true;
    }
}
