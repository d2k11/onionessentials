package net.onionmc.onionessentials.commands;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.models.UserSettings;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ColorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        String user = Usernames.get(player.getName());
        UserSettings settings = Usernames.getSettings(player.getName());

        if(strings.length == 0) {
            String colorName = "";
            player.sendMessage(Log.format("&7Your current color is " + settings.color + codeToColor(settings.color)));
            return true;
        }

        if(strings.length != 1) {
            player.sendMessage(Log.format("&cInvalid arguments."));
            return true;
        }

        List<String> validColors = List.of("green", "aqua", "red", "gray", "black", "white", "orange", "yellow");
        if(!validColors.contains(strings[0].toLowerCase())) {
            player.sendMessage(Log.format("&cInvalid color code. Options: &agreen, &baqua, &cred, &7gray, &0black, &fwhite, &6orange, &eyellow."));
            return true;
        }

        String colorCode = colorToCode(strings[0].toLowerCase());
        settings.color = colorCode;

        Usernames.putSettings(player.getName(), settings);

        player.sendMessage(Log.format("&7Your color is now " + settings.color + strings[0].toLowerCase()));
        return true;
    }

    public String codeToColor(String code) {
        String colorName = "";
        if(code.equals("&a")) {
            colorName = "green";
        }
        if(code.equals("&b")) {
            colorName = "aqua";
        }
        if(code.equals("&c")) {
            colorName = "red";
        }
        if(code.equals("&7")) {
            colorName = "gray";
        }
        if(code.equals("&0")) {
            colorName = "black";
        }
        if(code.equals("&f")) {
            colorName = "white";
        }
        if(code.equals("&6")) {
            colorName = "orange";
        }
        if(code.equals("&e")) {
            colorName = "yellow";
        }
        return colorName;
    }

    public String colorToCode(String color) {
        String code = "";
        if(color.equals("green")) {
            code = "&a";
        }
        if(color.equals("aqua")) {
            code = "&b";
        }
        if(color.equals("red")) {
            code = "&c";
        }
        if(color.equals("gray")) {
            code = "&7";
        }
        if(color.equals("black")) {
            code = "&0";
        }
        if(color.equals("white")) {
            code = "&f";
        }
        if(color.equals("orange")) {
            code = "&6";
        }
        if(color.equals("yellow")) {
            code = "&e";
        }
        return code;
    }
}
