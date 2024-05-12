package net.onionmc.onionessentials;

import net.onionmc.onionessentials.commands.*;
import net.onionmc.onionessentials.events.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class OnionEssentials extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvent(new OnPlayerJoin());
        registerEvent(new OnPlayerMove());
        registerEvent(new OnPlayerChat());
        registerEvent(new OnInventoryInteract());
        registerEvent(new OnPlayerLeave());
        registerEvent(new OnCommandCompletion());
        registerEvent(new OnServerMessage());
        registerEvent(new OnServerListPing());
        registerEvent(new OnBookSign());

        registerCommand("reset", new ResetCommand());
        registerCommand("tp", new TeleportCommand());
        registerCommand("say", new SayCommand());
        registerCommand("help", new HelpCommand());
        registerCommand("color", new ColorCommand());
        registerCommand("idle", new IdleCommand());
        registerCommand("yell", new YellCommand());
        registerCommand("hide", new HideCommand());
        registerCommand("channel", new ChannelCommand());
        registerCommand("list", new ListCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvent(Listener listener)
    {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void registerCommand(String commandString, CommandExecutor executor) {
        PluginCommand command = getCommand(commandString);
        if (command == null) return;
        command.setExecutor(executor);
    }

    private void registerScheduler(Runnable runnable, long delay, long period) {
        getServer().getScheduler().runTaskTimer(this, runnable, delay, period);
    }
}
