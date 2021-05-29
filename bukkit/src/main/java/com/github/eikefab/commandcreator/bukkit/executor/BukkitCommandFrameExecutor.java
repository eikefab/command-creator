package com.github.eikefab.commandcreator.bukkit.executor;

import com.github.eikefab.commandcreator.bukkit.context.BukkitContext;
import com.github.eikefab.commandcreator.frame.CommandFrame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class BukkitCommandFrameExecutor {

    private final CommandFrame commandFrame;

    public BukkitCommandFrameExecutor(CommandFrame commandFrame) {
        this.commandFrame = commandFrame;
    }

    public CommandFrame getCommandFrame() {
        return commandFrame;
    }

    public void execute(CommandSender executor, Command command, String[] args) {
        final Parameter parameter = commandFrame.getExecutor().getParameters()[0];

        if (parameter.getType().getName().equals(BukkitContext.class.getName())) {
            if (!(executor instanceof Player)) {
                executor.sendMessage("You must be an player!");

                return;
            }

            final Player player = (Player) executor;

            if (args.length == 0) {
                try {
                    commandFrame.getExecutor().invoke(commandFrame.getInstance(),
                            new BukkitContext(player, new String[] {}));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                for (CommandFrame frame : commandFrame.getSubCommands()) {
                    if (frame.getName().equals(args[0])) {

                    } else {
                        for (String alias : frame.getAliases()) {
                            if (alias.equals(args[0])) {

                            }
                        }
                    }
                }
            }
        }

    }
}
