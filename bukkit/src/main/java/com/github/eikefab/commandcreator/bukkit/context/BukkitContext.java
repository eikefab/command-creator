package com.github.eikefab.commandcreator.bukkit.context;

import com.github.eikefab.commandcreator.context.Context;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BukkitContext extends Context<Player> {

    private final Player executor;

    public BukkitContext(Player executor, String[] args) {
        super(executor, args);

        this.executor = executor;
    }

    @Override
    public boolean hasPermission(String permission) {
        return executor.hasPermission(permission);
    }

    @Override
    public void sendMessage(String content) {
        sendMessage(content, false);
    }

    public void sendMessage(String content, boolean translate) {
        if (translate) content = ChatColor.translateAlternateColorCodes('&', content);

        executor.sendMessage(content);
    }

    public void teleport(Location location) {
        executor.teleport(location);
    }

    public void open(Inventory inventory) {
        executor.openInventory(inventory);
    }

}
