package io.github.eikefab.libs.commandcreator.frame;

import io.github.eikefab.libs.commandcreator.type.CommandTarget;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;

@Builder
@Getter
public class CommandFrame {

    private final Method executor;
    private final Object instance;
    private final String name;
    private final String permission;
    private final String permissionMessage;
    private final String usage;
    private final String[] aliases;
    private final CommandTarget target;
    private final Parameter[] parameters;
    private final LinkedHashMap<String, CommandFrame> subCommands;
    private final boolean async;

}
