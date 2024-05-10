package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        player.getInventory().clear();
        player.teleport(new Location(player.getWorld(), 0, 0, 0));
        player.kick(Log.format("&aSuccessfully reset your session."));
        Usernames.deregister(player.getName());
        return true;
    }
}
