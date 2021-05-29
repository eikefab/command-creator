package com.github.eikefab.commandcreator.bukkit;

import com.github.eikefab.commandcreator.frame.CommandFrame;
import com.github.eikefab.commandcreator.reflection.CommandReader;
import org.bukkit.plugin.Plugin;

public final class BukkitCommandCreator {

    private final Plugin plugin;

    public BukkitCommandCreator(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void register(Object instance) {
        final CommandFrame frame = CommandReader.of(instance);

    }

    public void add(Object... instances) {
        for (Object instance : instances) register(instance);
    }

}
