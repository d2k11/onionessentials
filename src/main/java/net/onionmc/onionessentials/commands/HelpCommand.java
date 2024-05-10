package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(Log.format("&7&m-----------------------------"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7anonymous minecraft"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7commands:"));
        commandSender.sendMessage(Log.format("&7&m-----------------------------"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7/help &8- &7show this message"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7/reset &8- &7reset your session"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7/tp <x> <y> <z> &8- &7teleport to coordinates"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7/kill &8- &7kill yourself"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7/color <color> &8- &7change your chat color"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7/idle &8- &7go idle"));
        commandSender.sendMessage(Log.format("&8onion&7mc &8» &7/yell <message> &8- &7yell a message"));
        return false;
    }
}
