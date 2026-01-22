package edu.ewu.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for LearningModule abstract class.
 * Tests verify the abstract nature and shared functionality.
 */
@DisplayName("LearningModule")
class LearningModuleTest {

    // Concrete subclass for testing the abstract class
    private static class TestModule extends LearningModule {
        boolean sessionStarted = false;

        TestModule(String name) {
            super(name);
        }

        @Override
        public void startSession() {
            sessionStarted = true;
        }
    }

    @Nested
    @DisplayName("abstract class verification")
    class AbstractVerification {

        @Test
        @DisplayName("should be abstract")
        void shouldBeAbstract() {
            assertTrue(Modifier.isAbstract(LearningModule.class.getModifiers()));
        }

        @Test
        @DisplayName("startSession should be abstract")
        void startSessionShouldBeAbstract() throws NoSuchMethodException {
            assertTrue(Modifier.isAbstract(
                LearningModule.class.getDeclaredMethod("startSession").getModifiers()
            ));
        }
    }

    @Nested
    @DisplayName("construction")
    class Construction {

        @Test
        @DisplayName("should store module name")
        void shouldStoreModuleName() {
            TestModule module = new TestModule("Test Module");
            assertEquals("Test Module", module.getModuleName());
        }

        @Test
        @DisplayName("subclass can access moduleName")
        void subclassCanAccessModuleName() {
            TestModule module = new TestModule("My Module");
            // moduleName is protected, accessible in subclass
            assertEquals("My Module", module.getModuleName());
        }
    }

    @Nested
    @DisplayName("displayWelcome")
    class DisplayWelcome {

        @Test
        @DisplayName("should be concrete method")
        void shouldBeConcreteMethod() throws NoSuchMethodException {
            assertFalse(Modifier.isAbstract(
                LearningModule.class.getDeclaredMethod("displayWelcome").getModifiers()
            ));
        }

        @Test
        @DisplayName("should not throw exception")
        void shouldNotThrow() {
            TestModule module = new TestModule("Test");
            assertDoesNotThrow(() -> module.displayWelcome());
        }
    }

    @Nested
    @DisplayName("subclass behavior")
    class SubclassBehavior {

        @Test
        @DisplayName("subclass must implement startSession")
        void subclassMustImplementStartSession() {
            TestModule module = new TestModule("Test");
            module.startSession();
            assertTrue(module.sessionStarted);
        }
    }
}
