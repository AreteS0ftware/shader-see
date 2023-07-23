package it.aretesoftware.shadersee.shaderproperties.variables;

import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetUniformEvent;

@SuppressWarnings("All")
public abstract class VariableEventListener<TEVent extends SetUniformEvent> extends EventListener<TEVent> {

    protected VariableEventListener(Class<TEVent> clazz, Variable bind) {
        super(clazz, bind);
    }

    @Override
    protected boolean shouldFire(SetUniformEvent event) {
        return event.uniformName.equals(getBind().getVariableName());
    }

    @Override
    protected Variable getBind() {
        return (Variable) super.getBind();
    }


}
