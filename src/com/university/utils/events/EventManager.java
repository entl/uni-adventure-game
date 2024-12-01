package com.university.utils.events;

import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A singleton class that manages the registration and dispatching of events in the game
 * It allows components to register as listeners for specific event types and dispatches
 * events to the appropriate listeners when triggered
 */
public class EventManager {
    private static final ILogger logger = LoggerFactory.getLogger(EventManager.class);

    private static EventManager instance;

    private final Map<Class<? extends IEvent>, List<IEventListener>> listeners = new HashMap<>();

    /**
     * Private constructor to enforce singleton pattern
     */
    private EventManager() {
        logger.info("EventManager initialized.");
    }

    /**
     * Gets the singleton instance of the EventManager
     *
     * @return The singleton instance of EventManager
     */
    public static EventManager getInstance() {
        if (instance == null) {
            logger.info("Creating new EventManager instance.");
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Registers a listener for a specific type of event
     *
     * @param eventType The class type of the event to listen for
     * @param listener  The listener to register
     */
    public void registerListener(Class<? extends IEvent> eventType, IEventListener listener) {
        logger.info("Registering listener for event type: " + eventType.getName());
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
        logger.debug("Total listeners for " + eventType.getName() + ": " + listeners.get(eventType).size());
    }

    /**
     * Dispatches an event to all registered listeners for its type
     *
     * @param event The event to dispatch
     */
    public void dispatchEvent(IEvent event) {
        logger.info("Dispatching event: " + event.getClass().getName());
        List<IEventListener> eventListeners = listeners.get(event.getClass());

        if (eventListeners != null) {
            logger.debug("Found " + eventListeners.size() + " listener(s) for event: " + event.getClass().getName());
            for (IEventListener listener : eventListeners) {
                logger.debug("Notifying listener: " + listener.getClass().getName());
                listener.onEvent(event);
            }
        } else {
            logger.warning("No listeners found for event: " + event.getClass().getName());
        }
    }
}