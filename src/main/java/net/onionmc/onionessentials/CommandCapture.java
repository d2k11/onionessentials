package net.onionmc.onionessentials;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandCapture extends AbstractFilter {
    @Override
    public Result filter(LogEvent event) {
        String message = event.getMessage().getFormattedMessage();
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(message.toUpperCase().contains(player.getName().toUpperCase())) {
                return Result.DENY;
            }
        }
        return Result.NEUTRAL;
    }
}
