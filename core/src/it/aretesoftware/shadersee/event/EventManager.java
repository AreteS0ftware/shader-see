package it.aretesoftware.shadersee.event;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class EventManager {

    private final ObjectMap<Class<? extends Event>, Array<EventListener<?>>> events = new ObjectMap<>();
    private final ObjectMap<Class<? extends Event>, Array<EventListener<?>>> preEvents = new ObjectMap<>();

    /**
     * Registers a listener which fires before all `addListener` listeners fire.
     */
    public <TEvent extends Event> void addPreListener(EventListener<TEvent> listener) {
        if (!preEvents.containsKey(listener.getClazz()))
            preEvents.put(listener.getClazz(), new Array<EventListener<?>>());

        preEvents.get(listener.getClazz()).add(listener);
    }

    /**
     * Registers a listener which fires for that specified event.
     */
    public <TEvent extends Event> void addListener(EventListener<TEvent> listener)
    {
        if (!events.containsKey(listener.getClazz()))
            events.put(listener.getClazz(), new Array<EventListener<?>>());

        events.get(listener.getClazz()).add(listener);
    }

    //

    /**
     * Fires a specific event. Calling all `addPreListener` listeners first, then calling all `addListener`s.
     */
    public <TEvent extends Event> void fire(TEvent event)
    {
        fireOnList(event, null, preEvents);
        fireOnList(event, null, events);
    }

    public <TEvent extends Event> void fire(TEvent event, EventCallback<?> callback)
    {
        fireOnList(event, callback, preEvents);
        fireOnList(event, callback, events);
    }

    @SuppressWarnings("unchecked")
    private <TEvent extends Event> void fireOnList(TEvent event, EventCallback<?> eventCallback,
                                                   ObjectMap<Class<? extends Event>, Array<EventListener<?>>> events) {
        if (!events.containsKey(event.getClass())) {
            return;
        }

        Array<EventListener<?>> get = events.get(event.getClass());
        for (int i = 0; i < get.size; i++) {
            EventListener<?> eventListener = get.get(i);
            if (castAndFire(eventListener, event)) {
                castAndCallback(eventListener, eventCallback, event);
            }
        }
    }

    private <TEvent extends Event> boolean castAndFire(EventListener<?> eventListener, TEvent event) {
        if (eventListener.shouldCastAndFire(event)) {
            eventListener.castAndFire(event);
            return true;
        }
        return false;
    }

    private <TEvent extends Event> void castAndCallback(EventListener<?> eventListener, EventCallback<?> eventCallback, TEvent event) {
        if (eventCallback == null || !eventListener.shouldCastAndFireCallback(event)) {
            return;
        }
        Object callback = eventListener.getCallback();
        if (eventCallback.shouldCastAndCallback(callback)) {
            eventCallback.castAndCallback(callback);
        }
    }

    //

    public void removeListenersOfBind(Object bind, boolean identity) {
        removeListenersOfBind(preEvents, bind, identity);
        removeListenersOfBind(events, bind, identity);
    }

    private void removeListenersOfBind(ObjectMap<Class<? extends Event>, Array<EventListener<?>>> events, Object bind, boolean identity) {
        ObjectMap.Entries<Class<? extends Event>, Array<EventListener<?>>> entries = events.entries();
        while (entries.hasNext()) {
            ObjectMap.Entry<Class<? extends Event>, Array<EventListener<?>>> entry = entries.next();

            Array<EventListener<?>> listenersToDelete = new Array<>();
            Array<EventListener<?>> listeners = entry.value;
            for (int i = 0; i < listeners.size; i++) {
                EventListener<?> listener = listeners.get(i);
                if (identity || bind == null) {
                    if (bind == listener.getBind()) {
                        listenersToDelete.add(listener);
                    }
                }
                else {
                    if (bind.equals(listener.getBind())) {
                        listenersToDelete.add(listener);
                    }
                }
            }
            listeners.removeAll(listenersToDelete, true);
        }
    }

}
