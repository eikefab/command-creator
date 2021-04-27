package io.github.eikefab.libs.commandcreator.adapter.impl;

import io.github.eikefab.libs.commandcreator.adapter.CommandAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class PlayerAdapter implements CommandAdapter<Player> {

    @Override
    public Player adapt(String value) {
        return Bukkit.getPlayer(value);
    }

}
