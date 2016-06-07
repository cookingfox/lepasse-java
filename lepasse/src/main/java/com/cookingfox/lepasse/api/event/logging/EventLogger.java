package com.cookingfox.lepasse.api.event.logging;

import com.cookingfox.lepasse.api.event.Event;
import com.cookingfox.lepasse.api.state.State;

/**
 * Contains methods for logging event handling.
 *
 * @param <S> The concrete type of the state object.
 */
public interface EventLogger<S extends State> {

    /**
     * Called when an error occurs during the handling of an event.
     *
     * @param error    The error that occurred.
     * @param event    The event that was handled.
     * @param newState The new state that was returned by the event handler.
     */
    void onEventHandlerError(Throwable error, Event event, S newState);

    /**
     * Called when an event handler returns a result.
     *
     * @param event    The event that was handled.
     * @param newState The new state that was returned by the event handler.
     */
    void onEventHandlerResult(Event event, S newState);

}