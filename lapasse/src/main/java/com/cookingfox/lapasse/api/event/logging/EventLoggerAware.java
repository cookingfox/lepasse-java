package com.cookingfox.lapasse.api.event.logging;

import com.cookingfox.lapasse.api.lifecycle.Disposable;
import com.cookingfox.lapasse.api.state.State;

/**
 * Interface for elements that can have an event logger added.
 *
 * @param <S> The concrete type of the state object.
 */
public interface EventLoggerAware<S extends State> extends Disposable {

    /**
     * Add an event logger.
     *
     * @param logger The event logger to add.
     */
    void addEventLogger(EventLogger<S> logger);

    /**
     * Remove an event logger.
     *
     * @param logger The event logger to remove.
     */
    void removeEventLogger(EventLogger<S> logger);

}
