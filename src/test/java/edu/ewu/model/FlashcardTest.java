package edu.ewu.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for Flashcard - a question/answer pair for study.
 */
@DisplayName("Flashcard")
class FlashcardTest
{
    @Nested
    @DisplayName("construction")
    class Construction
    {
        /**
         * Tests if flashcard constructor sets question and answer
         * @author Julia Reynolds
         */
        @Test
        @DisplayName("should set fields")
        void constructorSetsFields()
        {
            Flashcard card = new Flashcard ("What is normal?", "Salmon");

            assertEquals("What is normal?", card.getQuestion());
            assertEquals("Salmon", card.getAnswer());
        }

        /**
         * Tests if flashcard throws an exception for a blank question or answer.
         * @author Julia Reynolds
         */
        @Test
        @DisplayName("constructor exceptions for invalid input")
        void constructorExceptions()
        {
            assertThrows(IllegalArgumentException.class, () -> {Flashcard card = new Flashcard(" ", "answer");});
            assertThrows(IllegalArgumentException.class, () -> new Flashcard("Question?", " "));
        }

        /**
         * Test checks if toString displays as intended
         * @author Julia Reynolds
         */
        @Test
        @DisplayName("toString returns question")
        void toStringShowsQuestion()
        {
            Flashcard card = new Flashcard ("What is the capital of France?", "Paris");

            assertTrue(card.toString().contains("What is the capital of France?"));
        }

        @Test
        @DisplayName("should create with question and answer")
        void shouldCreateWithQuestionAndAnswer()
        {
            Flashcard card = new Flashcard("What is encapsulation?", "Hiding internal state");
            assertNotNull(card);
        }

        @Test
        @DisplayName("should store question")
        void shouldStoreQuestion()
        {
            Flashcard card = new Flashcard("What is polymorphism?", "Answer");
            assertEquals("What is polymorphism?", card.getQuestion());
        }

        @Test
        @DisplayName("should store answer")
        void shouldStoreAnswer()
        {
            Flashcard card = new Flashcard("Question", "Child objects via parent type");
            assertEquals("Child objects via parent type", card.getAnswer());
        }

        @Test
        @DisplayName("should throw if question is null")
        void shouldThrowIfQuestionNull()
        {
            assertThrows(IllegalArgumentException.class, 
                () -> new Flashcard(null, "answer"));
        }

        @Test
        @DisplayName("should throw if answer is null")
        void shouldThrowIfAnswerNull()
        {
            assertThrows(IllegalArgumentException.class, 
                () -> new Flashcard("question", null));
        }

        @Test
        @DisplayName("should throw if question is blank")
        void shouldThrowIfQuestionBlank()
        {
            assertThrows(IllegalArgumentException.class, 
                () -> new Flashcard("   ", "answer"));
        }

        @Test
        @DisplayName("should throw if answer is blank")
        void shouldThrowIfAnswerBlank()
        {
            assertThrows(IllegalArgumentException.class, 
                () -> new Flashcard("question", ""));
        }
    }

    @Nested
    @DisplayName("equality")
    class EqualityTests
    {

        @Test
        @DisplayName("same question and answer should be equal")
        void sameShouldBeEqual()
        {
            Flashcard card1 = new Flashcard("Q", "A");
            Flashcard card2 = new Flashcard("Q", "A");
            assertEquals(card1, card2);
        }

        @Test
        @DisplayName("different question should not be equal")
        void differentQuestionNotEqual()
        {
            Flashcard card1 = new Flashcard("Q1", "A");
            Flashcard card2 = new Flashcard("Q2", "A");
            assertNotEquals(card1, card2);
        }

        @Test
        @DisplayName("equal cards should have same hashCode")
        void equalHashCodes()
        {
            Flashcard card1 = new Flashcard("Q", "A");
            Flashcard card2 = new Flashcard("Q", "A");
            assertEquals(card1.hashCode(), card2.hashCode());
        }

        /**
         * Checks if hash code for equal flashcards are the same
         * @author Julia Reynolds
         */
        @Test
        @DisplayName("hash set should have size 1 for duplicate cards since hash code should be equal")
        void worksWithHashSet()
        {
            Set<Flashcard> set = new HashSet<>();

            set.add(new Flashcard("Q", "A"));
            set.add(new Flashcard("Q", "A"));   // duplicate

            assertEquals(1, set.size());                  // should only have 1
        }

    }

    @Nested
    @DisplayName("toString")
    class ToStringTests
    {
        @Test
        @DisplayName("should contain question")
        void shouldContainQuestion()
        {
            Flashcard card = new Flashcard("What is OOP?", "Object-Oriented Programming");
            assertTrue(card.toString().contains("What is OOP?"));
        }
    }

    @Nested
    @DisplayName("load from file")
    class LoadFileTests
    {
        @Test
        @DisplayName("load from existing file")
        void loadCardsFromFile()
        {
            int             loaded;
            FlashcardModule module;

            module = new FlashcardModule();

            // Use the actual path to your test resources
            loaded = module.loadCardsFromFile("src/main/resources/flashcards.txt");

            assertTrue(loaded > 0, "Should load at least one card");
            assertEquals(loaded, module.getCardCount());
        }

        @Test
        @DisplayName("load from nonexistent file")
        void loadCardsFromNonexistentFile()
        {
            int             loaded;
            FlashcardModule module;

            module = new FlashcardModule();
            loaded = module.loadCardsFromFile("nonexistent.txt");

            assertEquals(0, loaded);
            assertEquals(0, module.getCardCount());
        }
    }

    @Nested
    @DisplayName("Clone Tests")
    class cloneTests
    {
        @Test
        void shallowCloneSharesMetadata()
        {
            // Simulating what happens without deep clone
            Flashcard original = new Flashcard("Q", "A");
            original.markReviewed();

            // If we only did super.clone() without cloning metadata:
            // Both would share the same metadata object
        }

        @Test
        void deepCloneHasIndependentMetadata()
        {
            Flashcard original = new Flashcard("Q", "A");
            original.markReviewed();  // timesReviewed = 1

            Flashcard cloned   = original.clone();
            cloned.markReviewed();    // Clone's timesReviewed = 2

            // Original should still be 1
            assertEquals(1, original.getMetadata().getTimesReviewed());
            assertEquals(2, cloned.getMetadata()  .getTimesReviewed());
        }

        @Test
        void clonedCardHasEqualContent()
        {
            Flashcard original = new Flashcard("Question", "Answer");
            Flashcard cloned   = original.clone();

            assertEquals(original.getQuestion(), cloned.getQuestion());
            assertEquals(original.getAnswer(),   cloned.getAnswer());
            assertNotSame(original, cloned);  // Different objects
        }
    }
}
