package com.github.eikefab.commandcreator.context;

public abstract class Context<A> {

    private final A executor;
    private final String[] args;

    public Context(A executor, String[] args) {
        this.executor = executor;
        this.args = args;
    }

    public abstract boolean hasPermission(String permission);
    public abstract void sendMessage(String content);

    public A getExecutor() {
        return executor;
    }

    public String getArg(int index) {
        return args[index];
    }

    public String[] getArgs() {
        return args;
    }

}

