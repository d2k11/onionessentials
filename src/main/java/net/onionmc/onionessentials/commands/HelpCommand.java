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
        commandSender.sendMessage(Log.format("&7&m-----------------------------"));
        commandSender.sendMessage(Log.format("&81. &7/help &8- &7show this message"));
        commandSender.sendMessage(Log.format("&82. &7/reset &8- &7reset your user id and settings"));
        commandSender.sendMessage(Log.format("&83. &7/tp <x> <y> <z> &8- &7teleport to coordinates"));
        commandSender.sendMessage(Log.format("&84. &7/kill &8- &7kill yourself"));
        commandSender.sendMessage(Log.format("&85. &7/color <color> &8- &7change your chat color"));
        commandSender.sendMessage(Log.format("&86. &7/idle &8- &7go idle"));
        commandSender.sendMessage(Log.format("&87. &7/yell <message> &8- &7yell a message"));
        commandSender.sendMessage(Log.format("&88. &7/hide &8- &7hide/show your player"));
        commandSender.sendMessage(Log.format("&89. &7/channel <channel> &8- &7change your chat channel"));
        commandSender.sendMessage(Log.format("&810. &7/list &8- &7list all players"));
        return false;
    }
}
