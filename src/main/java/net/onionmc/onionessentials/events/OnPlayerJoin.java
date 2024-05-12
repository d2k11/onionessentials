package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.Log;
import net.onionmc.onionessentials.models.UserSettings;
import net.onionmc.onionessentials.systems.Captcha;
import net.onionmc.onionessentials.systems.Usernames;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        event.joinMessage(null);
        Player player = event.getPlayer();
        Location previous = player.getLocation();

        Usernames.register(player.getName());
        Usernames.hide(player);

        String[] captchaInfo = Captcha.generateCaptcha();
        String solveString = captchaInfo[1];
        String challengeString = captchaInfo[0];

        for(int x = 0; x < 100; x++) player.sendMessage("");
        player.sendMessage(Log.format("&7&m----------------------------------------"));
        player.sendMessage(Log.format("&7PLEASE SOLVE THE CAPTCHA TO ENTER ONIONMC"));
        player.sendMessage(Log.format("&7&m----------------------------------------"));
        player.sendMessage("");
        player.sendMessage(Log.format(challengeString));
        player.sendMessage("");
        player.sendMessage(Log.format("&7&m----------------------------------------"));
        player.sendMessage(Log.format("&7Type the yellow text to chat and enter the server."));
        player.sendMessage(Log.format("&7&m----------------------------------------"));
        player.sendMessage("");

        for(Player onlinePlayer : player.getServer().getOnlinePlayers()) {
            for(Player onlinePlayer2 : player.getServer().getOnlinePlayers()) {
                UserSettings settings = Usernames.getSettings(onlinePlayer.getName());
                UserSettings settings2 = Usernames.getSettings(onlinePlayer2.getName());
                if(!settings.visible) {
                    onlinePlayer2.hidePlayer(onlinePlayer);
                }
                if(!settings2.visible) {
                    onlinePlayer.hidePlayer(onlinePlayer2);
                }
            }
        }

        Captcha.jail(player.getName(), solveString, previous);
    }
}
