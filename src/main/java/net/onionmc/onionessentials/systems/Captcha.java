package net.onionmc.onionessentials.systems;

import net.onionmc.onionessentials.Log;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Captcha {
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static HashMap<String, String> jailedUsers = new HashMap<>();
    private static HashMap<String, Location> previousLocations = new HashMap<>();
    public static String[] generateCaptcha() {
        String captchaString = "";
        String solveString = "";

        int[] positions = getCaptchaPositions();
        for (int i = 0; i < 48; i++) {
            // The captcha character and formatting
            String capChar = "";
            // If this captcha character is visible
            boolean shouldBeVisible = false;
            for(int x : positions) {
                if(x == i) {
                    shouldBeVisible = true;
                    break;
                }
            }
            // If it is visible, what formatting should it have?
            if(shouldBeVisible) {
                capChar = "&r";
                if(rand(0, 10) == 0) capChar += "&o";
                if(rand(0, 10) == 0) capChar += "&m";
            }
            // Otherwise it's obfuscated text
            else {
                capChar = "&r&k";
            }
            // Add the character to the captcha string
            String charRaw = alphabet.charAt(rand(0, alphabet.length() - 1)) + "";
            capChar += charRaw;
            captchaString += capChar;
            // If the character is visible, add it to the solve string
            if(shouldBeVisible) {
                solveString += charRaw;
            }
        }

        String[] captcha = {captchaString, solveString};
        return captcha;
    }

    public static int rand(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private static int[] getCaptchaPositions() {
        int[] positions = new int[6];
        for (int i = 0; i < 6; i++) {
            positions[i] = rand(0, 48);
        }
        return positions;
    }

    public static void jail(String username, String solve, Location old) {
        jailedUsers.put(username, solve);
        previousLocations.put(username, old);
        Player player = Bukkit.getPlayer(username);
        player.teleport(new Location(Bukkit.getWorld("world_the_end"), Captcha.rand(0, 10000), 1000, Captcha.rand(0, 10000)));
        player.setGameMode(GameMode.CREATIVE);
        player.setInvulnerable(true);
        player.setInvisible(true);
    }

    public static void unjail(String username) {
        jailedUsers.remove(username);
        Player player = Bukkit.getPlayer(username);
        if(previousLocations.get(username).getY() > 900 && previousLocations.get(username).getWorld().getName().equals("world_the_end")) {
            int x = Captcha.rand(0, 10000);
            int z = Captcha.rand(0, 10000);
            player.teleport(new Location(Bukkit.getWorld("world"), x, 100, z));
        }
        else {
            player.teleport(previousLocations.get(username));
        }
        previousLocations.remove(username);
        player.setGameMode(GameMode.CREATIVE);
        player.setInvulnerable(false);
        player.setInvisible(true);
        for(int xy = 0; xy < 100; xy++) player.sendMessage("");
        player.sendMessage(Log.format("&7&owelcome to &e&oonionmc"));
        Bukkit.getServer().broadcast(Log.format(Usernames.getSettings(player.getName()).color + Usernames.get(player.getName()) + " &a»"));
    }

    public static boolean isJailed(String username) {
        return jailedUsers.containsKey(username);
    }

    public static String getJailSolution(String username) {
        return jailedUsers.get(username);
    }
}
