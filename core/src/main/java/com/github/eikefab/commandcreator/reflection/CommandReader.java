package com.github.eikefab.commandcreator.reflection;

import com.github.eikefab.commandcreator.Command;
import com.github.eikefab.commandcreator.Default;
import com.github.eikefab.commandcreator.frame.CommandFrame;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CommandReader {

    public static CommandFrame of(Object instance) {
        final Class<?> clazz = instance.getClass();

        if (!(clazz.isAnnotationPresent(Command.class))) {
            throw new IllegalArgumentException(clazz.getName() + " must be annotated as Command.");
        }

        final Command command = clazz.getAnnotation(Command.class);
        final List<CommandFrame> subCommands = new ArrayList<>();

        Method defaultExecutor = null;
        for (Method method : clazz.getMethods()) {
            if (!method.isAccessible()) method.setAccessible(true);

            if (method.isAnnotationPresent(Default.class)) {
                defaultExecutor = method;
            }

            if (method.isAnnotationPresent(Command.class)) {
                if (method == defaultExecutor) {
                    throw new IllegalStateException(method.getName() + " is Default and Subcommand!");
                }

                subCommands.add(of(instance, method.getAnnotation(Command.class), new ArrayList<>(), method));
            }
        }

        return of(instance, command, subCommands, defaultExecutor);
    }

    private static CommandFrame of(Object instance, Command command, List<CommandFrame> subCommands, Method executor) {
        final String[] key = command.value();

        final String name = key[0];
        final String[] aliases = key.length == 1 ? null : Arrays.copyOfRange(key, 1, key.length);
        final String permission = command.permission();
        final boolean async = command.async();

        return new CommandFrame(instance, name, aliases, permission, subCommands, async, executor);
    }

}
