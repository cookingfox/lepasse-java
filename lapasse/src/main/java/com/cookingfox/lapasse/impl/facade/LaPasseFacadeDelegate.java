package com.cookingfox.lapasse.impl.facade;

import com.cookingfox.lapasse.api.command.Command;
import com.cookingfox.lapasse.api.command.handler.CommandHandler;
import com.cookingfox.lapasse.api.command.logging.CommandLogger;
import com.cookingfox.lapasse.api.event.Event;
import com.cookingfox.lapasse.api.event.handler.EventHandler;
import com.cookingfox.lapasse.api.event.logging.EventLogger;
import com.cookingfox.lapasse.api.facade.Facade;
import com.cookingfox.lapasse.api.logging.CombinedLogger;
import com.cookingfox.lapasse.api.state.State;
import com.cookingfox.lapasse.api.state.observer.OnStateChanged;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

/**
 * Delegates all {@link Facade} operations to a provided instance.
 */
public class LaPasseFacadeDelegate<S extends State> implements Facade<S> {

    /**
     * The facade instance to use as delegate.
     */
    protected final Facade<S> facade;

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    public LaPasseFacadeDelegate(Facade<S> facade) {
        this.facade = Objects.requireNonNull(facade, "Facade can not be null");
    }

    //----------------------------------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------------------------------

    @Override
    public void handleCommand(Command command) {
        facade.handleCommand(command);
    }

    @Override
    public <C extends Command, E extends Event> void mapCommandHandler(Class<C> commandClass, CommandHandler<S, C, E> commandHandler) {
        facade.mapCommandHandler(commandClass, commandHandler);
    }

    @Override
    public void setCommandHandlerExecutor(ExecutorService executor) {
        facade.setCommandHandlerExecutor(executor);
    }

    @Override
    public void addCommandLogger(CommandLogger logger) {
        facade.addCommandLogger(logger);
    }

    @Override
    public void dispose() {
        facade.dispose();
    }

    @Override
    public void handleEvent(Event event) {
        facade.handleEvent(event);
    }

    @Override
    public <E extends Event> void mapEventHandler(Class<E> eventClass, EventHandler<S, E> eventHandler) {
        facade.mapEventHandler(eventClass, eventHandler);
    }

    @Override
    public void addEventLogger(EventLogger<S> logger) {
        facade.addEventLogger(logger);
    }

    @Override
    public void addLogger(CombinedLogger<S> logger) {
        facade.addLogger(logger);
    }

    @Override
    public S getCurrentState() {
        return facade.getCurrentState();
    }

    @Override
    public void subscribe(OnStateChanged<S> subscriber) {
        facade.subscribe(subscriber);
    }

    @Override
    public void unsubscribe(OnStateChanged<S> subscriber) {
        facade.unsubscribe(subscriber);
    }

}
