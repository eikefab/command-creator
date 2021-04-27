package io.github.eikefab.libs.commandcreator.adapter;

import io.github.eikefab.libs.commandcreator.adapter.impl.PlayerAdapter;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class CommandAdapters {

    @Getter private static final Map<Class<?>, CommandAdapter<?>> adapters = new HashMap<>();

    static {
        adapters.put(Double.class, (CommandAdapter<Double>) NumberAdapter::adaptDouble);
        adapters.put(double.class, (CommandAdapter<Double>) NumberAdapter::adaptDouble);

        adapters.put(Integer.class, (CommandAdapter<Integer>) NumberAdapter::adaptInteger);
        adapters.put(int.class, (CommandAdapter<Integer>) NumberAdapter::adaptInteger);

        adapters.put(Long.class, (CommandAdapter<Long>) NumberAdapter::adaptLong);
        adapters.put(long.class, (CommandAdapter<Long>) NumberAdapter::adaptLong);

        adapters.put(Float.class, (CommandAdapter<Float>) NumberAdapter::adaptFloat);
        adapters.put(float.class, (CommandAdapter<Float>) NumberAdapter::adaptFloat);

        adapters.put(Player.class, new PlayerAdapter());
    }

    public static CommandAdapter<?> of(Class<?> type) {
        return adapters.getOrDefault(type, (CommandAdapter<String>) value -> value);
    }

}
