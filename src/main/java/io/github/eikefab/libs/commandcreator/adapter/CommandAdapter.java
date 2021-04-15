package io.github.eikefab.libs.commandcreator.adapter;

public interface CommandAdapter<T> {

    T adapt(String value);

}
