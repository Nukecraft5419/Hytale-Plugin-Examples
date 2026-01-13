package com.example.plugin;

import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.example.plugin.commands.CameraCommand;
import com.example.plugin.commands.ExampleCommand;
import com.example.plugin.commands.TitleCommand;
import com.example.plugin.listeners.PlayerChatListener;
import com.example.plugin.listeners.PlayerReadyListener;

import javax.annotation.Nonnull;

public class ExamplePlugin extends JavaPlugin {
    public ExamplePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        // Commands
        this.getCommandRegistry().registerCommand(new CameraCommand());
        this.getCommandRegistry().registerCommand(new ExampleCommand());
        this.getCommandRegistry().registerCommand(new TitleCommand());

        // Events
        this.getEventRegistry().registerGlobal(PlayerChatEvent.class, PlayerChatListener::onPlayerChat);
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, PlayerReadyListener::onPlayerReady);
    }
}