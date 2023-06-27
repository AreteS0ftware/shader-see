package it.aretesoftware.shadersee.event;

public abstract class EventCallback<CObject> {

    private final Class<CObject> callbackObjectClass;

    public EventCallback(Class<CObject> callbackObjectClass) {
        this.callbackObjectClass = callbackObjectClass;
    }

    protected abstract void callback(CObject callback);

    @SuppressWarnings("unchecked")
    final <C> void castAndCallback(C callback) {
        callback((CObject) callback);
    }

    final <C> boolean shouldCastAndCallback(C callback) {
        return callbackObjectClass.isAssignableFrom(callback.getClass());
    }

    Class<CObject> getCallbackObjectClass() {
        return callbackObjectClass;
    }
}
