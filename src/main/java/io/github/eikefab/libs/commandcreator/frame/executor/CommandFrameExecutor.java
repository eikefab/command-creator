package io.github.eikefab.libs.commandcreator.frame.executor;

import io.github.eikefab.libs.commandcreator.adapter.CommandAdapter;
import io.github.eikefab.libs.commandcreator.adapter.CommandAdapters;
import io.github.eikefab.libs.commandcreator.frame.CommandFrame;
import io.github.eikefab.libs.commandcreator.type.CommandTarget;
import lombok.Data;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

@Data(staticConstructor = "of")
public class CommandFrameExecutor {

    private final CommandFrame commandFrame;

    public void execute(CommandSender sender, String[] args) {
        final CommandTarget commandTarget = commandFrame.getTarget();
        final Map<String, CommandFrame> subCommands = commandFrame.getSubCommands();
        final Parameter[] parameters = commandFrame.getParameters();

        if (commandTarget != CommandTarget.BOTH) {
            if (sender instanceof Player && commandTarget == CommandTarget.CONSOLE) {
                sender.sendMessage("§cYou must be execute this command as console!");

                return;
            }

            if ((!(sender instanceof Player)) && commandTarget == CommandTarget.PLAYER) {
                sender.sendMessage("§cYou must be execute this command as player!");

                return;
            }
        }

        if (!sender.hasPermission(commandFrame.getPermission())) {
            sender.sendMessage(commandFrame.getPermissionMessage());

            return;
        }

        if ((parameters.length > args.length) || (subCommands.size() > args.length)) {
            sender.sendMessage(commandFrame.getUsage());

            return;
        }

        for (Map.Entry<String, CommandFrame> entry : subCommands.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(args[0])) continue;

            final CommandFrameExecutor executor = new CommandFrameExecutor(entry.getValue());

            final String[] executorArgs = args.length == 1 ?
                    new String[] {} :
                    Arrays.copyOfRange(args, 1, args.length);

            executor.execute(sender, executorArgs);
        }

        LinkedList<Object> values = new LinkedList<>();

        for (int position = 0; position < parameters.length; position++) {
            final Parameter parameter = parameters[position];
            final CommandAdapter<?> adapter = CommandAdapters.of(parameter.getType());
            final Object value = adapter.adapt(args[position]);

            values.add(value);
        }

        try {
            commandFrame.getExecutor().invoke(commandFrame, sender, values.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
