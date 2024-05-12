package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.models.UserSettings;
import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage(Log.format("&7&m-----------------------------"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7anonymous minecraft"));
        commandSender.sendMessage(Log.format("&7&m-----------------------------"));
        int x = 0;
        for(Player player : commandSender.getServer().getOnlinePlayers()) {
            x++;
            UserSettings settings = Usernames.getSettings(player.getName());
            String username = Usernames.get(player.getName());
            if(Captcha.isJailed(player.getName())) {
                commandSender.sendMessage(Log.format("&8"+x+". " + settings.color+username+"&8 » &7Idle in Captcha Jail"));
                continue;
            }
            if(settings.idle) {
                commandSender.sendMessage(Log.format("&8"+x+". " + settings.color+username+"&8 » &7Idle in Channel "+settings.channel));
                continue;
            }
            commandSender.sendMessage(Log.format("&8"+x+". " + settings.color+username+"&8 » &7Talking in Channel "+settings.channel));
        }
        commandSender.sendMessage(Log.format("&7&m-----------------------------"));
        return true;
    }
}
