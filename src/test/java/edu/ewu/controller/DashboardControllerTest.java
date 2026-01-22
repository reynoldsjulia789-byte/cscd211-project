package edu.ewu.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

public class DashboardControllerTest
{
    // DashboardControllerTest.java
    @Test
    void controllerInitializesWithCards()
    {
        DashboardController controller;

        controller = new DashboardController();

        assertTrue(controller.getCardCount() > 0);
    }

    @Test
    void getCurrentQuestionReturnsValidString()
    {
        DashboardController controller;
        String              question;

        controller = new DashboardController();

        controller.handlePracticeFlashcards();

        question = controller.getCurrentQuestion();

        assertNotNull(question);
        assertFalse(question.isEmpty());
    }
}
