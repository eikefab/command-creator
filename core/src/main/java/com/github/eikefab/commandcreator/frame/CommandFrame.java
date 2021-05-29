package com.github.eikefab.commandcreator.frame;

import java.lang.reflect.Method;
import java.util.List;

public class CommandFrame {

    private final Object instance;
    private final String name;
    private final String[] aliases;
    private final String permission;
    private final List<CommandFrame> subCommands;
    private final boolean async;
    private final Method executor;

    public CommandFrame(Object instance, String name, String[] aliases,
                        String permission, List<CommandFrame> subCommands,
                        boolean async, Method executor) {
        this.instance = instance;
        this.name = name;
        this.aliases = aliases;
        this.permission = permission;
        this.subCommands = subCommands;
        this.async = async;
        this.executor = executor;
    }

    public Object getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public Object getPermission() {
        return permission;
    }

    public List<CommandFrame> getSubCommands() {
        return subCommands;
    }

    public boolean isAsync() {
        return async;
    }

    public Method getExecutor() {
        return executor;
    }

}
