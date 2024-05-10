package net.onionmc.onionessentials.systems;

import net.onionmc.onionessentials.models.UserSettings;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Usernames {
    private static HashMap<String, Integer> usernames = new HashMap<>();
    private static HashMap<String, UserSettings> settings = new HashMap<>();

    public static void register(String username) {
        if(!usernames.containsKey(username)) {
            usernames.put(username, Captcha.rand(1000, 9999));
            settings.put(username, new UserSettings());
        }
    }

    public static void deregister(String username)
    {
        settings.remove(username);
        usernames.remove(username);
    }

    public static void hide(Player player) {
        player.setCustomName("User " + usernames.get(player.getName()));
        player.setDisplayName("User " + usernames.get(player.getName()));
        player.setPlayerListName("User " + usernames.get(player.getName()));
        List<String> commandCompletions = new ArrayList<>();
        commandCompletions = OnionApi.getBaitUsernames();
        player.setCustomChatCompletions(commandCompletions);
    }

    public static String get(String username) {
        return "User " + usernames.get(username);
    }

    public static UserSettings getSettings(String username) {
        return settings.get(username);
    }

    public static UserSettings putSettings(String username, UserSettings userSettings) {
        return settings.put(username, userSettings);
    }
}
