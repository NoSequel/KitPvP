package io.github.nosequel.kitpvp.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class HandlerManager {

    private final Map<Class<? extends Handler>, Handler> cache = new HashMap<>();

    /**
     * Find a handler by a class
     *
     * @param clazz the class to find the handler by
     * @param <T>   the type of the handler
     * @return the found handler
     */
    public <T extends Handler> T find(Class<T> clazz) {
        return clazz.cast(this.cache.get(clazz));
    }

    /**
     * Register a new handler
     *
     * @param handler the handler to regis
     */
    public void register(Handler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("Provided handler in HandlerManager#register is null");
        }

        this.cache.put(handler.getClass(), handler);
    }

    /**
     * Open a new stream of the cache values list
     *
     * @return the newly opened stream
     */
    public Stream<Handler> stream() {
        return this.cache.values().stream();
    }
}