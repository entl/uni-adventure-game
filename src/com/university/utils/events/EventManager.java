package com.university.utils.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A singleton class that manages the registration and dispatching of events in the game.
 * It allows components to register as listeners for specific event types and dispatches
 * events to the appropriate listeners when triggered.
 */
public class EventManager {
    private static EventManager instance;

    private final Map<Class<? extends IEvent>, List<IEventListener>> listeners = new HashMap<>();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private EventManager() {}

    /**
     * Gets the singleton instance of the EventManager.
     *
     * @return The singleton instance of EventManager.
     */
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Registers a listener for a specific type of event.
     *
     * @param eventType The class type of the event to listen for.
     * @param listener  The listener to register.
     */
    public void registerListener(Class<? extends IEvent> eventType, IEventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    /**
     * Dispatches an event to all registered listeners for its type.
     *
     * @param event The event to dispatch.
     */
    public void dispatchEvent(IEvent event) {
        List<IEventListener> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (IEventListener listener : eventListeners) {
                listener.onEvent(event);
            }
        }
    }
}