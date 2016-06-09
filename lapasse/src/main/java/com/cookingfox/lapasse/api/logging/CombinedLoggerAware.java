package com.cookingfox.lapasse.api.logging;

import com.cookingfox.lapasse.api.state.State;

/**
 * Interface for elements that can have a combined logger added.
 *
 * @param <S> The concrete type of the state object.
 */
public interface CombinedLoggerAware<S extends State> {

    /**
     * Add a logger instance that implements all supported logger interfaces.
     *
     * @param logger The logger to add.
     */
    void addLogger(CombinedLogger<S> logger);

}
