package net.runelite.client.plugins.xo.utils.data;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.Plugin;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Singleton
public class Cache {

    private final Map<Class<? extends Plugin>, Map<Class<?>, Object>> container = new HashMap<>();

    public <T extends Plugin, U> void put(Class<T> pluginType, Class<U> valueType, U value) {
        container.computeIfAbsent(pluginType, k -> new HashMap<>()).put(valueType, value);
    }

    public <T, U> Optional<U> get(Class<T> pluginType, Class<U> valueType) {
        U value = null;

        Map<Class<?>, Object> pluginContainer = container.get(pluginType);
        if (pluginContainer != null) {
            value = valueType.cast(pluginContainer.get(valueType));
        }

        return Optional.ofNullable(value);
    }

    public <T extends Plugin, U> void remove(Class<T> pluginType, Class<U> valueType) {
        put(pluginType, valueType, null);
    }

}
