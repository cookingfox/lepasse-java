package com.cookingfox.lapasse.compiler;

import com.cookingfox.lapasse.impl.helper.LaPasseHelper;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

/**
 * Unit tests for {@link LaPasseAnnotationProcessor}.
 */
public class LaPasseAnnotationProcessorTest {

    //----------------------------------------------------------------------------------------------
    // VOID COMMAND HANDLER
    //----------------------------------------------------------------------------------------------

    @Test
    public void void_command_handler() throws Exception {
        String sourceFqcn = "test.Test";
        String expectedFqcn = sourceFqcn + LaPasseHelper.GENERATED_SUFFIX;

        JavaFileObject source = JavaFileObjects.forSourceLines(sourceFqcn,
                "package test;",
                "",
                "import com.cookingfox.lapasse.annotation.HandleCommand;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.state.CountState;",
                "",
                "public class Test {",
                "    @HandleCommand",
                "    public void handle(CountState state, IncrementCount command) {",
                "    }",
                "}"
        );

        JavaFileObject expected = JavaFileObjects.forSourceLines(expectedFqcn,
                "// Generated code from LaPasse - do not modify!",
                "package test;",
                "",
                "import com.cookingfox.lapasse.api.command.handler.VoidCommandHandler;",
                "import com.cookingfox.lapasse.api.facade.Facade;",
                "import com.cookingfox.lapasse.impl.internal.HandlerMapper;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.state.CountState;",
                "import java.lang.Override;",
                "",
                "public class Test$$LaPasseGenerated<T extends Test> implements HandlerMapper {",
                "    final T origin;",
                "",
                "    final Facade facade;",
                "",
                "    final VoidCommandHandler<CountState, IncrementCount> _1 = new VoidCommandHandler<CountState, IncrementCount>() {",
                "        @Override",
                "        public void handle(CountState state, IncrementCount command) {",
                "            origin.handle(state, command);",
                "        }",
                "    };",
                "",
                "    public Test$$LaPasseGenerated(T origin, Facade facade) {",
                "        this.origin = origin;",
                "        this.facade = facade;",
                "    }",
                "",
                "    @Override",
                "    public void mapHandlers() {",
                "        facade.mapCommandHandler(IncrementCount.class, _1);",
                "    }",
                "}"
        );

        assertAbout(javaSource()).that(source)
                .processedWith(new LaPasseAnnotationProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    //----------------------------------------------------------------------------------------------
    // SYNC COMMAND HANDLER
    //----------------------------------------------------------------------------------------------

    @Test
    public void sync_command_handler() throws Exception {
        String sourceFqcn = "test.Test";
        String expectedFqcn = sourceFqcn + LaPasseHelper.GENERATED_SUFFIX;

        JavaFileObject source = JavaFileObjects.forSourceLines(sourceFqcn,
                "package test;",
                "",
                "import com.cookingfox.lapasse.annotation.HandleCommand;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "",
                "public class Test {",
                "    @HandleCommand",
                "    public CountIncremented handle(CountState state, IncrementCount command) {",
                "        return new CountIncremented(command.getCount());",
                "    }",
                "}"
        );

        JavaFileObject expected = JavaFileObjects.forSourceLines(expectedFqcn,
                "// Generated code from LaPasse - do not modify!",
                "package test;",
                "",
                "import com.cookingfox.lapasse.api.command.handler.SyncCommandHandler;",
                "import com.cookingfox.lapasse.api.facade.Facade;",
                "import com.cookingfox.lapasse.impl.internal.HandlerMapper;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.lang.Override;",
                "",
                "public class Test$$LaPasseGenerated<T extends Test> implements HandlerMapper {",
                "    final T origin;",
                "",
                "    final Facade facade;",
                "",
                "    final SyncCommandHandler<CountState, IncrementCount, CountIncremented> _1 = new SyncCommandHandler<CountState, IncrementCount, CountIncremented>() {",
                "        @Override",
                "        public CountIncremented handle(CountState state, IncrementCount command) {",
                "            return origin.handle(state, command);",
                "        }",
                "    };",
                "",
                "    public Test$$LaPasseGenerated(T origin, Facade facade) {",
                "        this.origin = origin;",
                "        this.facade = facade;",
                "    }",
                "",
                "    @Override",
                "    public void mapHandlers() {",
                "        facade.mapCommandHandler(IncrementCount.class, _1);",
                "    }",
                "}"
        );

        assertAbout(javaSource()).that(source)
                .processedWith(new LaPasseAnnotationProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    //----------------------------------------------------------------------------------------------
    // SYNC MULTI COMMAND HANDLER
    //----------------------------------------------------------------------------------------------

    @Test
    public void sync_multi_command_handler() throws Exception {
        String sourceFqcn = "test.Test";
        String expectedFqcn = sourceFqcn + LaPasseHelper.GENERATED_SUFFIX;

        JavaFileObject source = JavaFileObjects.forSourceLines(sourceFqcn,
                "package test;",
                "",
                "import com.cookingfox.lapasse.annotation.HandleCommand;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.util.Arrays;",
                "import java.util.Collection;",
                "",
                "public class Test {",
                "    @HandleCommand",
                "    public Collection<CountIncremented> handle(CountState state, IncrementCount command) {",
                "        return Arrays.asList(new CountIncremented(command.getCount()));",
                "    }",
                "}"
        );

        JavaFileObject expected = JavaFileObjects.forSourceLines(expectedFqcn,
                "// Generated code from LaPasse - do not modify!",
                "package test;",
                "",
                "import com.cookingfox.lapasse.api.command.handler.SyncMultiCommandHandler;",
                "import com.cookingfox.lapasse.api.facade.Facade;",
                "import com.cookingfox.lapasse.impl.internal.HandlerMapper;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.lang.Override;",
                "import java.util.Collection;",
                "",
                "public class Test$$LaPasseGenerated<T extends Test> implements HandlerMapper {",
                "    final T origin;",
                "",
                "    final Facade facade;",
                "",
                "    final SyncMultiCommandHandler<CountState, IncrementCount, CountIncremented> _1 = new SyncMultiCommandHandler<CountState, IncrementCount, CountIncremented>() {",
                "        @Override",
                "        public Collection<CountIncremented> handle(CountState state, IncrementCount command) {",
                "            return origin.handle(state, command);",
                "        }",
                "    };",
                "",
                "    public Test$$LaPasseGenerated(T origin, Facade facade) {",
                "        this.origin = origin;",
                "        this.facade = facade;",
                "    }",
                "",
                "    @Override",
                "    public void mapHandlers() {",
                "        facade.mapCommandHandler(IncrementCount.class, _1);",
                "    }",
                "}"
        );

        assertAbout(javaSource()).that(source)
                .processedWith(new LaPasseAnnotationProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    //----------------------------------------------------------------------------------------------
    // ASYNC COMMAND HANDLER
    //----------------------------------------------------------------------------------------------

    @Test
    public void async_command_handler() throws Exception {
        String sourceFqcn = "test.Test";
        String expectedFqcn = sourceFqcn + LaPasseHelper.GENERATED_SUFFIX;

        JavaFileObject source = JavaFileObjects.forSourceLines(sourceFqcn,
                "package test;",
                "",
                "import com.cookingfox.lapasse.annotation.HandleCommand;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.util.concurrent.Callable;",
                "",
                "public class Test {",
                "    @HandleCommand",
                "    public Callable<CountIncremented> handle(CountState state, final IncrementCount command) {",
                "        return new Callable<CountIncremented>() {",
                "            @Override",
                "            public CountIncremented call() throws Exception {",
                "                return new CountIncremented(command.getCount());",
                "            }",
                "        };",
                "    }",
                "}"
        );

        JavaFileObject expected = JavaFileObjects.forSourceLines(expectedFqcn,
                "// Generated code from LaPasse - do not modify!",
                "package test;",
                "",
                "import com.cookingfox.lapasse.api.command.handler.AsyncCommandHandler;",
                "import com.cookingfox.lapasse.api.facade.Facade;",
                "import com.cookingfox.lapasse.impl.internal.HandlerMapper;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.lang.Override;",
                "import java.util.concurrent.Callable;",
                "",
                "public class Test$$LaPasseGenerated<T extends Test> implements HandlerMapper {",
                "    final T origin;",
                "",
                "    final Facade facade;",
                "",
                "    final AsyncCommandHandler<CountState, IncrementCount, CountIncremented> _1 = new AsyncCommandHandler<CountState, IncrementCount, CountIncremented>() {",
                "        @Override",
                "        public Callable<CountIncremented> handle(CountState state, IncrementCount command) {",
                "            return origin.handle(state, command);",
                "        }",
                "    };",
                "",
                "    public Test$$LaPasseGenerated(T origin, Facade facade) {",
                "        this.origin = origin;",
                "        this.facade = facade;",
                "    }",
                "",
                "    @Override",
                "    public void mapHandlers() {",
                "        facade.mapCommandHandler(IncrementCount.class, _1);",
                "    }",
                "}"
        );

        assertAbout(javaSource()).that(source)
                .processedWith(new LaPasseAnnotationProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    //----------------------------------------------------------------------------------------------
    // ASYNC MULTI COMMAND HANDLER
    //----------------------------------------------------------------------------------------------

    @Test
    public void async_multi_command_handler() throws Exception {
        String sourceFqcn = "test.Test";
        String expectedFqcn = sourceFqcn + LaPasseHelper.GENERATED_SUFFIX;

        JavaFileObject source = JavaFileObjects.forSourceLines(sourceFqcn,
                "package test;",
                "",
                "import com.cookingfox.lapasse.annotation.HandleCommand;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.util.Arrays;",
                "import java.util.Collection;",
                "import java.util.concurrent.Callable;",
                "",
                "public class Test {",
                "    @HandleCommand",
                "    public Callable<Collection<CountIncremented>> handle(CountState state, final IncrementCount command) {",
                "        return new Callable<Collection<CountIncremented>>() {",
                "            @Override",
                "            public Collection<CountIncremented> call() throws Exception {",
                "                return Arrays.asList(new CountIncremented(command.getCount()));",
                "            }",
                "        };",
                "    }",
                "}"
        );

        JavaFileObject expected = JavaFileObjects.forSourceLines(expectedFqcn,
                "// Generated code from LaPasse - do not modify!",
                "package test;",
                "",
                "import com.cookingfox.lapasse.api.command.handler.AsyncMultiCommandHandler;",
                "import com.cookingfox.lapasse.api.facade.Facade;",
                "import com.cookingfox.lapasse.impl.internal.HandlerMapper;",
                "import fixtures.example.command.IncrementCount;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.lang.Override;",
                "import java.util.Collection;",
                "import java.util.concurrent.Callable;",
                "",
                "public class Test$$LaPasseGenerated<T extends Test> implements HandlerMapper {",
                "    final T origin;",
                "",
                "    final Facade facade;",
                "",
                "    final AsyncMultiCommandHandler<CountState, IncrementCount, CountIncremented> _1 = new AsyncMultiCommandHandler<CountState, IncrementCount, CountIncremented>() {",
                "        @Override",
                "        public Callable<Collection<CountIncremented>> handle(CountState state, IncrementCount command) {",
                "            return origin.handle(state, command);",
                "        }",
                "    };",
                "",
                "    public Test$$LaPasseGenerated(T origin, Facade facade) {",
                "        this.origin = origin;",
                "        this.facade = facade;",
                "    }",
                "",
                "    @Override",
                "    public void mapHandlers() {",
                "        facade.mapCommandHandler(IncrementCount.class, _1);",
                "    }",
                "}"
        );

        assertAbout(javaSource()).that(source)
                .processedWith(new LaPasseAnnotationProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

    //----------------------------------------------------------------------------------------------
    // VALID EVENT HANDLER
    //----------------------------------------------------------------------------------------------

    @Test
    public void valid_event_handler() throws Exception {
        String sourceFqcn = "test.Test";
        String expectedFqcn = sourceFqcn + LaPasseHelper.GENERATED_SUFFIX;

        JavaFileObject source = JavaFileObjects.forSourceLines(sourceFqcn,
                "package test;",
                "",
                "import com.cookingfox.lapasse.annotation.HandleEvent;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "",
                "public class Test {",
                "    @HandleEvent",
                "    public CountState handle(CountState state, CountIncremented event) {",
                "        return new CountState(state.getCount() + event.getCount());",
                "    }",
                "}"
        );

        JavaFileObject expected = JavaFileObjects.forSourceLines(expectedFqcn,
                "// Generated code from LaPasse - do not modify!",
                "package test;",
                "",
                "import com.cookingfox.lapasse.api.event.handler.EventHandler;",
                "import com.cookingfox.lapasse.api.facade.Facade;",
                "import com.cookingfox.lapasse.impl.internal.HandlerMapper;",
                "import fixtures.example.event.CountIncremented;",
                "import fixtures.example.state.CountState;",
                "import java.lang.Override;",
                "",
                "public class Test$$LaPasseGenerated<T extends Test> implements HandlerMapper {",
                "    final T origin;",
                "",
                "    final Facade facade;",
                "",
                "    final EventHandler<CountState, CountIncremented> _1 = new EventHandler<CountState, CountIncremented>() {",
                "        @Override",
                "        public CountState handle(CountState state, CountIncremented event) {",
                "            return origin.handle(state, event);",
                "        }",
                "    };",
                "",
                "    public Test$$LaPasseGenerated(T origin, Facade facade) {",
                "        this.origin = origin;",
                "        this.facade = facade;",
                "    }",
                "",
                "    @Override",
                "    public void mapHandlers() {",
                "        facade.mapEventHandler(CountIncremented.class, _1);",
                "    }",
                "}"
        );

        assertAbout(javaSource()).that(source)
                .processedWith(new LaPasseAnnotationProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(expected);
    }

}