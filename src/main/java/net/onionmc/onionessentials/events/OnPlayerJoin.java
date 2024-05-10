package net.onionmc.onionessentials.events;

import net.onionmc.onionessentials.Log;
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

        for(Player onlinePlayer : player.getServer().getOnlinePlayers()) {
            for(Player onlinePlayer2 : player.getServer().getOnlinePlayers()) {
                onlinePlayer2.hidePlayer(onlinePlayer);
                onlinePlayer.hidePlayer(onlinePlayer2);
            }
        }

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
        player.sendMessage(Log.format("&7Type the unobfuscated text to chat and enter the server."));
        player.sendMessage(Log.format("&7&m----------------------------------------"));
        player.sendMessage("");

        Captcha.jail(player.getName(), solveString, previous);
    }
}
