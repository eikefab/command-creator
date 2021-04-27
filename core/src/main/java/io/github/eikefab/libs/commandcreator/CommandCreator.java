package io.github.eikefab.libs.commandcreator;

import io.github.eikefab.libs.commandcreator.frame.CommandFrame;
import io.github.eikefab.libs.commandcreator.frame.CommandFrame.CommandFrameBuilder;
import io.github.eikefab.libs.commandcreator.frame.executor.CommandFrameExecutor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Data
public final class CommandCreator {

    private final JavaPlugin plugin;

    public void add(Object... commands) {
        for (Object instance : commands) {
            try {
                register(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void register(Object instance) throws IllegalAccessException {
        final Class<?> clazz = instance.getClass();

        for (Method method : clazz.getMethods()) {
            if (!method.isAnnotationPresent(Command.class)) continue;
            if (!method.isAccessible()) method.setAccessible(true);

            final Command command = method.getAnnotation(Command.class);
            final CommandFrameBuilder frameBuilder = CommandFrame.builder();

            final String[] keys = command.name().split(" ");
            final Parameter[] parameters = method.getParameters();

            String id = keys[0];

            if (id.startsWith("/")) id = id.substring(1);

            frameBuilder.name(id);

            /** for (String key : Arrays.copyOfRange(keys, 1, keys.length)) {
                TODO: allow sub commands
            } **/

            if (parameters.length == 0) {
                throw new IllegalAccessException("First parameter must be CommandSender!");
            }

            if (!parameters[0].getType().getName().equals(CommandSender.class.getName())) {
                throw new IllegalAccessException("First parameter must be CommandSender!");
            }

            if (parameters.length >= 2) {
                frameBuilder.parameters(Arrays.copyOfRange(parameters, 1, parameters.length));
            } else {
                frameBuilder.parameters(new Parameter[] {});
            }

            frameBuilder.target(command.target())
                    .async(command.async())
                    .aliases(command.aliases())
                    .usage(command.usage())
                    .permission(command.permission())
                    .permissionMessage(command.permissionMessage())
                    .instance(instance)
                    .executor(method)
                    .subCommands(new LinkedHashMap<>());

            register(frameBuilder.build());
        }
    }

    public void register(CommandFrame commandFrame) {
        final PluginCommand command = plugin.getCommand(commandFrame.getName());

        command.setAliases(Arrays.asList(commandFrame.getAliases()));
        command.setPermission(commandFrame.getPermission());
        command.setPermissionMessage(commandFrame.getPermissionMessage());
        command.setExecutor((sender, cmd, label, args) -> {
            final CommandFrameExecutor executor = CommandFrameExecutor.of(commandFrame);

            if (commandFrame.isAsync()) {
                Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> executor.execute(sender, args));
            } else {
                executor.execute(sender, args);
            }

            return false;
        });
    }

}
