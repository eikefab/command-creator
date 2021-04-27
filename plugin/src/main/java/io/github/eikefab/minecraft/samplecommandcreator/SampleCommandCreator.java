package io.github.eikefab.minecraft.samplecommandcreator;

import io.github.eikefab.libs.commandcreator.Command;
import io.github.eikefab.libs.commandcreator.CommandCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class SampleCommandCreator extends JavaPlugin {

    @Override
    public void onEnable() {
        final CommandCreator commandCreator = new CommandCreator(this);

        commandCreator.add(this);
    }

    @Command(name = "ping")
    public void handle(CommandSender sender) {
        sender.sendMessage("§aPong!");
    }

}
